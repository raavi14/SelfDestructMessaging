package com.mfp.services.messaging.service.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.mfp.services.messaging.service.dto.MessageDTO;

public interface MessageDao {
		
	/**
	 * Method to insert new message in unexpired messages table
	 * @param messageDTO Contains message data to be inserted
	 * @return Message Id of the inserted message
	 * @throws Exception
	 */
	String insertUnExpiredMessage(MessageDTO messageDTO) throws Exception;
	
	/**
	 * Method to insert new message in expired messages table
	 * @param messageDTO
	 * @return Message Id of the inserted message
	 * @throws Exception
	 */
	String insertExpiredMessage(MessageDTO messageDTO) throws Exception;
	
	/**
	 * Method to delete a message from unexpired messages table
	 * @param messageDTO Contains message data to be deleted
	 * @return status of the operation
	 * @throws Exception
	 */
	boolean deleteUnExpiredMessage(MessageDTO messageDTO) throws Exception;
	
    /**
     * @param messageId Message id of the message to be retrieved
     * @return Message Object
     */
    MessageDTO getMessage(String messageId) throws EmptyResultDataAccessException;
    
    /**
     * @param username Username of the messages to be retrieved
     * @return List of Message Objects
     * @throws Exception 
     */
    List<MessageDTO> getMessagesByUsername(String username) throws Exception;

}
