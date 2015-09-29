package com.mfp.services.messaging.service.response;

import java.io.Serializable;

public class MessageResponse implements Serializable {	
	
	private static final long serialVersionUID = -2867441451628880473L;
	private String messageId;
	private String username;
	private String text;
	private String expirationDate;
	
	/**
	 * @return Id Message Id
	 */
	public String getMessageId() {
		return messageId;
	}
	
	/**
	 * @param Id Message Id
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * @return username 
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username Message username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text message text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return expirationDate Message Expiration Date
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	
	/**
	 * @param expirationDate Message Expiration Date
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
