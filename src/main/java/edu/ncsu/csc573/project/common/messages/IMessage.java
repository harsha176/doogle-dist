package edu.ncsu.csc573.project.common.messages;

/**
 * This interface abstracts the message part of the response. It is assumed that
 * response message is in format of field value pairs.
 * 
 * @author doogle-dev
 * 
 */
public interface IMessage {
	/**
	 * This method is used to fetch a param value based on its field.
	 * @param param
	 * @return
	 */
	public Object getParam(EnumParamsType param);
}
