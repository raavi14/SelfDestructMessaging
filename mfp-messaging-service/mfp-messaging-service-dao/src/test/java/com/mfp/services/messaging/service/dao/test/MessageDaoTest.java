package com.mfp.services.messaging.service.dao.test;


import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mfp.services.messaging.service.dao.MessageDao;
import com.mfp.services.messaging.service.dto.MessageDTO;


@ContextConfiguration(locations = { "classpath:dao-context-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTest {
	
	@Autowired
    MessageDao messageDao;
	
	private String messageId="12342455";
	private String userName="paul";
   
	@Test
    public void testGetMessageByMessageId() {
		MessageDTO messageDTO=messageDao.getMessage(messageId);
		Assert.assertEquals("sandeep",messageDTO.getUsername());
		Assert.assertEquals("hello world",messageDTO.getText());
		Assert.assertEquals("2011-12-18 13:17:17",messageDTO.getExpirationDate());		
    }
	
	@Test
    public void testGetMessagesByUsername() throws Exception {
		List<MessageDTO> messageDTOs=messageDao.getMessagesByUsername(userName);
		Assert.assertEquals(3,messageDTOs.size());
		Assert.assertEquals("hi",messageDTOs.get(0).getText());	
		Assert.assertEquals("how are you?",messageDTOs.get(1).getText());	
		Assert.assertEquals("im good",messageDTOs.get(2).getText());	
    }
	
	@Test
    public void testInsertMessage() throws Exception {
		MessageDTO messageDTO=new MessageDTO();
		messageDTO.setMessageId("23432432432");
		messageDTO.setUsername("randy");
		messageDTO.setText("message test");
		messageDTO.setExpirationDate("2011-12-18 13:17:25");
		String messageId=messageDao.insertUnExpiredMessage(messageDTO);
		messageDTO=messageDao.getMessage(messageId);
		Assert.assertEquals("randy",messageDTO.getUsername());
		Assert.assertEquals("message test",messageDTO.getText());
    }
	
	@Test
    public void testDeleteMessage() throws Exception {
		MessageDTO messageDTO=new MessageDTO();
		messageDTO.setMessageId("23432432490");
		messageDTO.setUsername("randy");
		messageDTO.setText("message test");
		messageDTO.setExpirationDate("2011-12-18 13:17:59");
		String messageId=messageDao.insertUnExpiredMessage(messageDTO);		
		messageDTO=messageDao.getMessage(messageId);
		boolean status=messageDao.deleteUnExpiredMessage(messageDTO);
		Assert.assertTrue(status);	
    }

}
