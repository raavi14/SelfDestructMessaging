package com.mfp.services.messaging.service.dto;

public class MessageDTO {
	
	private String messageId;
	private String username;
	private String text;
	private String expirationDate;
	
	/**
	 * @return Message Id
	 */
	public String getMessageId() {
		return messageId;
	}
	
	/**
	 * @param messageId
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * @return Message Username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return Message Text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return Expiration Date
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	
	/**
	 * @param expirationDate
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

}
