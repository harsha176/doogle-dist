package edu.ncsu.csc573.project.common.messages;

import java.util.Set;

/**
 * This interface abstracts the notion of parameter. It is assumed that each
 * parameter has a field and value.
 * 
 * @author doogle-dev
 * 
 */
public interface IParameter {
	/**
	 * This method adds param value entry in Parameters object.
	 * @param param
	 * @param value
	 */
	void add(EnumParamsType param, Object value);
	
	/**
	 * This method get value of the parameter based on the parameter type
	 * 
	 * @param paramType parameter for which value will be fetched
	 * @return
	 */
	Object getParamValue(EnumParamsType paramType);
	
	/**
	 * This method gets the list of all the parameters configured in the request.
	 * 
	 * @return
	 */
	Set<EnumParamsType> getAllParams();
}
