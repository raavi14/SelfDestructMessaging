package com.mfp.services.messaging.service.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.mfp.services.messaging.service.dao.constants.MessageDAOConstants;
import com.mfp.services.messaging.service.dto.MessageDTO;
import com.mfp.services.messaging.service.dto.mapper.MessageDTOMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mfp.services.messaging.service.utils.MessageUtil;
;

public class MessageDaoImpl implements MessageDao {

	private DataSource dataSource;

	private MessageDTO messageDTO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageDaoImpl.class);

	public MessageDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public MessageDTO getMessage(String messageId) throws EmptyResultDataAccessException {
		try 
		{
			LOGGER.debug("getMessage {} ",messageId);
			int size = (int) getJdbcTemplate().queryForInt(
					MessageDAOConstants.SELECT_COUNT_BY_MESSAGEID_SQL,
					new Object[] { messageId });
			if (size != 0) 
			{
				messageDTO = (MessageDTO) getJdbcTemplate().queryForObject(MessageDAOConstants.SELECT_MESSAGE_UNEXPIRED_SQL,
						    new Object[] { messageId }, new MessageDTOMapper());
			} 
			else 
			{
				messageDTO = (MessageDTO) getJdbcTemplate().queryForObject(MessageDAOConstants.SELECT_MESSAGE_EXPIRED_SQL,
						    new Object[] { messageId }, new MessageDTOMapper());
			}
			return messageDTO;
		} 
		catch (EmptyResultDataAccessException e)
		{
			LOGGER.error("An error occured due to empty result set for "+messageId);
			throw e;
		}
	}

	public List<MessageDTO> getMessagesByUsername(String username) throws Exception {
		try
		{
			LOGGER.debug("getMessagesByUsername {} ",username);
			List<MessageDTO> messages = new ArrayList<MessageDTO>();
			List rows = getJdbcTemplate().queryForList(
					MessageDAOConstants.SELECT_BY_USERNAME_SQL,
					new Object[] { username });			
			for (Object row : rows) {
				HashMap<String, Object> map = (HashMap<String, Object>) row;
				MessageDTO messageDTO = new MessageDTO();
				messageDTO.setMessageId((String) map.get("MessageId"));
				messageDTO.setUsername((String) map.get("Username"));
				messageDTO.setText((String) map.get("Text"));
				messageDTO.setExpirationDate(MessageUtil
						.getExpirationDate((Timestamp) map
								.get("ExpirationDate")));
				messages.add(messageDTO);
			}
		  return messages;
		}
		catch(Exception e)
		{
			LOGGER.error("An error occured while retrieving result set for "+username);
			throw e;			
		}
		
	}

	public String insertUnExpiredMessage(MessageDTO messageDTO)
			throws Exception {
		try {
			String messageId = messageDTO.getMessageId();
			String username = messageDTO.getUsername();
			String text = messageDTO.getText();
			String expirationDate = messageDTO.getExpirationDate();
			getJdbcTemplate().update(
					MessageDAOConstants.INSERT_NEW_MESSAGE_SQL,
					new Object[] { messageId, username, text, expirationDate });
			return messageId;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String insertExpiredMessage(MessageDTO messageDTO) throws Exception {
		try {
			String messageId = messageDTO.getMessageId();
			String username = messageDTO.getUsername();
			String text = messageDTO.getText();
			String expirationDate = messageDTO.getExpirationDate();
			getJdbcTemplate().update(
					MessageDAOConstants.INSERT_NEW__EXPRIED_MESSAGE_SQL,
					new Object[] { messageId, username, text, expirationDate });
			return messageId;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean deleteUnExpiredMessage(MessageDTO messageDTO)
			throws Exception {
		try {
			String messageId = messageDTO.getMessageId();
			getJdbcTemplate().update(MessageDAOConstants.DELETE_MESSAGE_SQL,
					new Object[] { messageId });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

}
