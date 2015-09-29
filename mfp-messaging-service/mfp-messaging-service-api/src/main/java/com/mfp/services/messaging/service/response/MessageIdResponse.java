package com.mfp.services.messaging.service.response;

import java.io.Serializable;

public class MessageIdResponse implements Serializable {
	
	private static final long serialVersionUID = -6172398748949037465L;
	private String Id;

	/**
	 * @return Id Message Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param Id Message Id
	 */
	public void setId(String id) {
		Id = id;
	}

}
