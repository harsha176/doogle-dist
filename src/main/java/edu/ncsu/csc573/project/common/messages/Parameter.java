package edu.ncsu.csc573.project.common.messages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parameter implements IParameter {
	private Map<EnumParamsType, Object> params = new HashMap<EnumParamsType, Object>();
	
	public void add(EnumParamsType param, Object value) {
		params.put(param, value);
	}

	public Object getParamValue(EnumParamsType paramType) {
		return params.get(paramType);
	}

	public Set<EnumParamsType> getAllParams() {
		return params.keySet();
	}

}
