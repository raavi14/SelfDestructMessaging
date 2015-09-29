package com.mfp.services.messaging.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mfp.services.messaging.service.MessagingServiceImpl;
import com.mfp.services.messaging.service.dao.MessageDao;
import com.mfp.services.messaging.service.dao.MessageDaoImpl;
import com.mfp.services.messaging.service.dto.MessageDTO;
import com.mfp.services.messaging.service.response.MessageContentResponse;
import com.mfp.services.messaging.service.response.MessageResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagingServiceTest {
	
	private final MessageDao messageDao = mock(MessageDaoImpl.class);	
	private final MessagingServiceImpl messagingService=new MessagingServiceImpl();
	private MessageDTO messageDTO;
	private MessageDTO messageDTO2;
	private List<MessageDTO> messages;
	private MessageResponse messageResponse;
	private String messageId="90840";
	private String messageIdNull="90841";
	private String usernameTest="sandeep_ravi";
	private String usernameNull="Chuck Norris";
	
	@Before
    public void beforeTest() throws Exception
    {
		messagingService.setMessagingDao(messageDao);
		messageDTO=new MessageDTO();
		messageDTO2=new MessageDTO();
		messages=new ArrayList<MessageDTO>();
		messageDTO.setMessageId("90840");
		messageDTO.setUsername("sandeep_ravi");
		messageDTO.setText("Hello World");
		messageDTO.setExpirationDate("2015-09-20 09:23:05");
		messageDTO2.setMessageId("90844");
		messageDTO2.setUsername("sandeep_ravi");
		messageDTO2.setText("Hi");
		messageDTO2.setExpirationDate("2015-09-20 09:23:10");
		messages.add(messageDTO);
		messages.add(messageDTO2);
		when(messageDao.getMessage(messageId)).thenReturn(messageDTO);
		when(messageDao.getMessage(messageIdNull)).thenReturn(null);
		when(messageDao.getMessagesByUsername(usernameTest)).thenReturn(messages);
		when(messageDao.getMessagesByUsername(usernameNull)).thenReturn(null);
    }

    @Test
    public void testGetMessageByMessageId()
    {
    	messageResponse=messagingService.getMessageById(messageId);
    	Assert.assertEquals("90840",messageResponse.getMessageId());
    	Assert.assertEquals("sandeep_ravi",messageResponse.getUsername());
    	Assert.assertEquals("Hello World",messageResponse.getText());
    	Assert.assertEquals("2015-09-20 09:23:05",messageResponse.getExpirationDate());
    }
    
    @Test
    public void testGetMessageByMessageIdForNullResponse()
    {
    	messageResponse=messagingService.getMessageById(messageIdNull);
    	Assert.assertNull(messageResponse);
    }
    
    @Test
    public void testGetMessagesByUsernameForSize() throws Exception
    {
    	List<MessageContentResponse> messageDTOs=messagingService.getUnExpiredMessagesByUsername(usernameTest);
    	Assert.assertEquals(2,messageDTOs.size());
    }
    
    @Test
    public void testGetMessagesByUsernameForValues() throws Exception
    {
    	List<MessageContentResponse> messageDTOs=messagingService.getUnExpiredMessagesByUsername(usernameTest);
    	Assert.assertEquals("90840",messageDTOs.get(0).getId());    	
    	Assert.assertEquals("Hello World",messageDTOs.get(0).getText());
    	Assert.assertEquals("90844",messageDTOs.get(1).getId());    	
    	Assert.assertEquals("Hi",messageDTOs.get(1).getText());    	
    }
    
    @Test
    public void testGetMessagesByUsernameForNull() throws Exception
    {
    	List<MessageContentResponse> messageDTOs=messagingService.getUnExpiredMessagesByUsername(usernameNull);
    	Assert.assertEquals(0,messageDTOs.size());
    }  	
    

}
