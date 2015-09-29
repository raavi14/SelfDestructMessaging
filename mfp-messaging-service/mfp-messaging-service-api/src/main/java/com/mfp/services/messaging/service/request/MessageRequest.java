package com.mfp.services.messaging.service.request;

import java.io.Serializable;

public class MessageRequest implements Serializable {

	private static final long serialVersionUID = -29856668577571162L;
	private String username;
	private String text;
	private int timeout;

	/**
	 * @return username message username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username message username to set
	 * @throws Exception
	 */
	public void setUsername(String username) throws Exception {
		if (username == null) {
			throw new Exception("Username is null");
		}
		this.username = username;
	}

	/**
	 * @return text message text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text message text to set
	 * @throws Exception
	 */
	public void setText(String text) throws Exception {
		if (text == null) {
			throw new Exception("Text is null");
		}
		this.text = text;
	}

	/**
	 * @return timeout timeout period of the message
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout timeout period of the message
	 */
	public void setTimeout(int timeout) {
		if (timeout == 0) {
			this.timeout = 60;
		}
		this.timeout = timeout;
	}
}
