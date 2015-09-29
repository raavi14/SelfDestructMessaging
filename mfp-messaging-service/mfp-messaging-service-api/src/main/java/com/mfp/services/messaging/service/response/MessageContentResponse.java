package com.mfp.services.messaging.service.response;

import java.io.Serializable;

public class MessageContentResponse implements Serializable {
	
	private static final long serialVersionUID = -9148401764417378368L;

	private String id;
	
	private String text;

	/**
	 * @return id  Message Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id Message Id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return text Message Text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text Message Text
	 */
	public void setText(String text) {
		this.text = text;
	}

}
