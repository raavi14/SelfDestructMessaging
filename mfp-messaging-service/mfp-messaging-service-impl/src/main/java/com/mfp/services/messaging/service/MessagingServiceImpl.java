package com.mfp.services.messaging.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.mfp.services.messaging.service.dao.MessageDao;
import com.mfp.services.messaging.service.dto.MessageDTO;
import com.mfp.services.messaging.service.request.MessageRequest;
import com.mfp.services.messaging.service.response.MessageContentResponse;
import com.mfp.services.messaging.service.response.MessageResponse;
import com.mfp.services.messaging.service.utils.MessageUtil;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("com.mfp.services.messaging.service.MessagingService")
@Component
public class MessagingServiceImpl implements MessagingService {	
	
	private Timer timer;
	
	private MessageDao messageDao;
	
	private MessageResponse messageResponse;
	
	@Autowired
    public void setMessagingDao(final MessageDao messageDao)
    {
        this.messageDao = messageDao;
    }

	public MessageResponse getMessageById(String messageId) {		
		messageResponse=new MessageResponse();	
	    MessageDTO messageDTO=messageDao.getMessage(messageId);
		if(messageDTO!=null)
		{
			messageResponse.setMessageId(messageDTO.getMessageId());
			messageResponse.setUsername(messageDTO.getUsername());
			messageResponse.setText(messageDTO.getText());
			messageResponse.setExpirationDate(messageDTO.getExpirationDate());
			return messageResponse;
		}	
		else
		{
			return null;
		}
	}

	public List<MessageContentResponse> getUnExpiredMessagesByUsername(String username) throws Exception {
		List<MessageContentResponse> messageContentResponses = new ArrayList<MessageContentResponse>();
		List<MessageDTO> messages = messageDao.getMessagesByUsername(username);
		if (messages != null) {
			expireMessages(messages);
			for (MessageDTO message : messages) {
				MessageContentResponse messageContentResponse = new MessageContentResponse();
				messageContentResponse.setId(message.getMessageId());
				messageContentResponse.setText(message.getText());
				messageContentResponses.add(messageContentResponse);
			}
		}
		return messageContentResponses;
	}

	public String insertMessage(MessageRequest messageRequest) throws Exception 
	{	
		MessageDTO messageDTO=new MessageDTO();		
		messageDTO.setMessageId(MessageUtil.generateUniqueKey());
		messageDTO.setUsername(messageRequest.getUsername());
		messageDTO.setText(messageRequest.getText());		
		messageDTO.setExpirationDate(MessageUtil.getExpirationDate(messageRequest.getTimeout()));
		timer = new Timer();
	    timer.schedule(new ExpireTask(messageDTO,timer),MessageUtil.getTimeout(messageRequest.getTimeout()));
		return messageDao.insertUnExpiredMessage(messageDTO);
	}

	/**
	 * Method to expire a message
	 * @param message message to expire
	 * @return boolean status of the operation
	 * @throws Exception
	 */
	public boolean expireMessage(MessageDTO message) throws Exception {		
		messageDao.deleteUnExpiredMessage(message);
		messageDao.insertExpiredMessage(message);
		return true;
	}

	/**
	 * Method to expire group of messages
	 * @param messages List of messages to expire
	 * @return boolean status of the operation
	 * @throws Exception
	 */
	public boolean expireMessages(List<MessageDTO> messages) throws Exception {
		if(messages!=null)
		{
			for(MessageDTO message:messages)
			{
				expireMessage(message);
			}
			return true;
		}
		return false;
	}	
	
	
	/**
	 * Timer Task class used in Message Timers
	 *
	 */
	public class ExpireTask extends TimerTask{
		
		MessageDTO messageDTO;
		Timer timer;
		
		public ExpireTask(MessageDTO messageDTO, Timer timer) 
		{
			this.messageDTO=messageDTO;
			this.timer=timer;
		}

		@Override
		public void run() {
			try 
			{
				//expire message after timeout
				expireMessage(messageDTO);	
				timer.cancel();
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();
			}
		}
	}
	
}
