/**
 * 
 */
package edu.ncsu.csc573.project.common.messages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author doogle-dev
 *
 */
public class RequestParameters implements IParameter {
	private Map<EnumParamsType,Object> params; 

	public RequestParameters() {
		params = new HashMap<EnumParamsType, Object>();
	}
	
	/* (non-Javadoc)
	 * @see edu.ncsu.csc573.project.common.messages.IParameter#add(edu.ncsu.csc573.project.common.messages.EnumParamsType, java.lang.Object)
	 */
	public void add(EnumParamsType param, Object value) {
		params.put(param, value);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc573.project.common.messages.IParameter#getParamValue(edu.ncsu.csc573.project.common.messages.EnumParamsType)
	 */
	public Object getParamValue(EnumParamsType param) {
		return params.get(param);
	}

	public Set<EnumParamsType> getAllParams() {
		return params.keySet();
	}

}
