package com.mfp.services.messaging.service.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.springframework.jdbc.core.RowMapper;
import com.mfp.services.messaging.service.dto.MessageDTO;
import com.mfp.services.messaging.service.utils.MessageUtil;

/**
 * Mapper class to map the content from the result set to the MessageDTO Object
 *
 */
public class MessageDTOMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessageId(rs.getString("MessageId"));
			messageDTO.setUsername(rs.getString("Username"));
			messageDTO.setText(rs.getString("Text"));
			Timestamp timestamp = rs.getTimestamp("ExpirationDate");			
			messageDTO.setExpirationDate(MessageUtil.getExpirationDate(timestamp));
			return messageDTO;		
	}
	
}
