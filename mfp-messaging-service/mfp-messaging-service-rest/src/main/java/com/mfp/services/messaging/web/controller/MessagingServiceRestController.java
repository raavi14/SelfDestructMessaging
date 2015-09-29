package com.mfp.services.messaging.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mfp.services.messaging.service.MessagingService;
import com.mfp.services.messaging.service.request.MessageRequest;
import com.mfp.services.messaging.service.response.MessageContentResponse;
import com.mfp.services.messaging.service.response.MessageIdResponse;
import com.mfp.services.messaging.service.response.MessageResponse;

@Controller
public class MessagingServiceRestController {
	
	@Autowired
	private MessagingService messagingService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagingServiceRestController.class);
	
	/*
	 * Method: healthcheck
	 * This method is used for troubleshooting the application installation status
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody String healthcheck(HttpServletRequest httpReq, HttpServletResponse httpResp) {
		return "Text Messaging Service is Live";
	}
	
	/**
	 * @param messageReq Message Request Containing the message object
	 * @param httpResp HttpServletResponse Object Containing HTTP Status
	 * @return JSON containing the Id of the inserted message	 
	 */
	@RequestMapping(value="/chat", 
			consumes={"application/json"},
			produces={"application/json"},
			method=RequestMethod.POST)	
    public @ResponseBody MessageIdResponse postMessageRequest(@RequestBody final MessageRequest messageReq,HttpServletResponse httpResp) 
    {
		try
		{
		 LOGGER.debug("New Message Recieved for "+messageReq.getUsername());
		 String messageId= messagingService.insertMessage(messageReq);		 
		 MessageIdResponse messageIdResponse=new MessageIdResponse();
		 messageIdResponse.setId(messageId);		 
		 httpResp.setStatus(HttpServletResponse.SC_CREATED);
		 LOGGER.debug("Message has been successfully created MessageId: "+messageId);
		 return messageIdResponse;
		}
		catch(Exception e)
		{
			LOGGER.error("An Error occurred while inserting new message Cause: "+e.getMessage());
			httpResp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
    }
	
	/**
	 * @param messageId Message Id to retrieve the Message Object
	 * @param httpResp HttpServletResponse Object Containing HTTP Status
	 * @return JSON containing the Message Object
	 */
	@RequestMapping(value = " /chat/{messageId}", method=RequestMethod.GET,produces={"application/json"})
	public @ResponseBody MessageResponse getMessageById(@PathVariable String messageId,HttpServletResponse httpResp){
		try
		{
		 LOGGER.debug("Request recieved to retrieve message with messageId: "+messageId);
		 MessageResponse messageResponse= messagingService.getMessageById(messageId);		 	 
		 httpResp.setStatus(HttpServletResponse.SC_OK);
		 LOGGER.debug("Message has been successfully retrieved");
		 return messageResponse;
		}
		catch(Exception e)
		{			
			LOGGER.error("An Error occurred while retrieving message with messageId: "+messageId+" Cause: "+e.getMessage());
			httpResp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}		
	}
	
	/**
	 * @param username Username to retrieve messages
	 * @param httpResp HttpServletResponse Object Containing HTTP Status
	 * @return JSON List of Message Objects containing messageId and text
	 */
	@RequestMapping(value = " /chats/{username}", method=RequestMethod.GET,produces={"application/json"})
	public @ResponseBody List<MessageContentResponse> getMessagesByUsername(@PathVariable String username,HttpServletResponse httpResp){
		try
		{
		 LOGGER.debug("Request recieved to retrieve messages for username: "+username);
		 List<MessageContentResponse> messages= messagingService.getUnExpiredMessagesByUsername(username);		 
		 httpResp.setStatus(HttpServletResponse.SC_OK);
		 LOGGER.debug("Messages have been successfully retrieved");
		 return messages;
		}
		catch(Exception e)
		{
			LOGGER.error("An Error occurred while retrieving messages for username: "+username+" Cause: "+e.getMessage());
			httpResp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}		
	}
}
