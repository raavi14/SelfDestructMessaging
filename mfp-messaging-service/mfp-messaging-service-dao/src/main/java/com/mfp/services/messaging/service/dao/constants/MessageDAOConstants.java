package com.mfp.services.messaging.service.dao.constants;

/** 
 * Class contains SQL Prepared statements used in the Message DAO layer 
 */
public class MessageDAOConstants {	
	
	public static final String INSERT_NEW_MESSAGE_SQL="INSERT INTO UNEXPIRED_MESSAGES (MESSAGEID,USERNAME,TEXT,EXPIRATIONDATE) "
			                                          + "VALUES(?,?,?,?)";
	
	public static final String INSERT_NEW__EXPRIED_MESSAGE_SQL="INSERT INTO EXPIRED_MESSAGES (MESSAGEID,USERNAME,TEXT,EXPIRATIONDATE) "
                                                              + "VALUES(?,?,?,?)";
	
	public static final String SELECT_MESSAGE_UNEXPIRED_SQL="SELECT MESSAGEID,USERNAME,TEXT,EXPIRATIONDATE FROM UNEXPIRED_MESSAGES"
			                                                + " WHERE MESSAGEID=?";
	
	public static final String SELECT_COUNT_BY_MESSAGEID_SQL="SELECT Count(*) FROM UNEXPIRED_MESSAGES WHERE MESSAGEID=?";
	
	public static final String SELECT_MESSAGE_EXPIRED_SQL="SELECT MESSAGEID,USERNAME,TEXT,EXPIRATIONDATE FROM EXPIRED_MESSAGES"
                                                        + " WHERE MESSAGEID=?";
	
	public static final String SELECT_BY_USERNAME_SQL="SELECT MESSAGEID,USERNAME,TEXT,EXPIRATIONDATE FROM UNEXPIRED_MESSAGES"
                                                     + " WHERE USERNAME=?";
	
	public static final String DELETE_MESSAGE_SQL="DELETE FROM UNEXPIRED_MESSAGES WHERE MESSAGEID=?";
}
