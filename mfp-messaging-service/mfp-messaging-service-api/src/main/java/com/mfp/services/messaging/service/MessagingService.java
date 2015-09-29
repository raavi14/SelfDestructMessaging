package com.mfp.services.messaging.service;

import java.util.List;

import javax.naming.NamingException;

import com.mfp.services.messaging.service.request.MessageRequest;
import com.mfp.services.messaging.service.response.MessageContentResponse;
import com.mfp.services.messaging.service.response.MessageResponse;

public interface MessagingService {	
	
	/**
	 * @param messageId MessageId to retrieve the message object
	 * @return MessageResponse contains content of the retrieved message
	 * @throws Exception
	 */
	MessageResponse getMessageById(String messageId);
	
	/**
	 * @param username  Username to retrieve messages
	 * @return List of MessageContentResponse objects
	 * @throws Exception
	 */
	List<MessageContentResponse> getUnExpiredMessagesByUsername(String username) throws Exception;
	
	/**
	 * @param messageRequest New Message to insert
	 * @return messageId Message Id of the inserted message
	 * @throws Exception
	 */
	String insertMessage(MessageRequest messageRequest) throws Exception;

}
