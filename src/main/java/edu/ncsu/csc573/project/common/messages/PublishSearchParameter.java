package edu.ncsu.csc573.project.common.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class implements a complex parameter. It can include multiple IParameter
 * objects
 * 
 * This is used for publish request parameters and search response parameters as
 * these include multiple file parameters
 * 
 * @author doogle-dev
 * 
 */
public class PublishSearchParameter implements IParameter {
	/**
	 * It will store multiple parameters.
	 */
	private List<IParameter> complexParam = new ArrayList<IParameter>();
	private int count = 0;
	private IParameter current_param = null;

	/**
	 * This method adds particular param and value. It expects each IParameter
	 * set is separated by <b>EnumParamType.DELIMETER</b>
	 */
	public void add(EnumParamsType param, Object value) {

		if (count == 0 && current_param == null) {
			current_param = new Parameter();
		} else if (param == EnumParamsType.DELIMITER) {
			current_param.add(EnumParamsType.DELIMITER, count);
			complexParam.add(current_param);
			count++;
			current_param = new Parameter();
			return;
		}
		current_param.add(param, value);
	}

	/**
	 * This method adds a PublishSearchParameter
	 * 
	 * @param pubSearchParam
	 */
	public void add(PublishSearchParameter pubSearchParam) {
		if (this == pubSearchParam) {
			return;
		}
		pubSearchParam.resetCounter();
		while (pubSearchParam.getParamCount() < pubSearchParam.getSize()) {
			this.add(EnumParamsType.FILENAME,
					pubSearchParam.getParamValue(EnumParamsType.FILENAME));
			this.add(EnumParamsType.FILEDIGEST,
					pubSearchParam.getParamValue(EnumParamsType.FILEDIGEST));
			this.add(EnumParamsType.FILESIZE,
					pubSearchParam.getParamValue(EnumParamsType.FILESIZE));
			this.add(EnumParamsType.ABSTRACT,
					pubSearchParam.getParamValue(EnumParamsType.ABSTRACT));
			this.add(EnumParamsType.IPADDRESS,
					pubSearchParam.getParamValue(EnumParamsType.IPADDRESS));
			this.add(EnumParamsType.DELIMITER, null);
			pubSearchParam.setNextParam();
		}
	}

	/**
	 * This method should be called before iterating through this complex
	 * parameter
	 */
	public void resetCounter() {
		count = 0;
	}

	/**
	 * This method retrieves particular param value based on the particular
	 * stage in iteration that is count.
	 */
	public Object getParamValue(EnumParamsType paramType) {
		if (count >= complexParam.size()) {
			return null;
		}
		IParameter param = complexParam.get(count);
		return param.getParamValue(paramType);
	}

	/**
	 * This method should be called before moving to next set of parameters
	 */
	public void setNextParam() {
		count++;
	}

	/**
	 * This method retrieves all the params in IParameter set.
	 * @param complex parameter set
	 */
	public Set<EnumParamsType> getAllParams() {
		return complexParam.get(0).getAllParams();
	}

	/**
	 * This method gets the current set in the IParameter.
	 * 
	 * @return
	 */
	public int getParamCount() {
		return count;
	}

	/**
	 * This method gets the number IParameter sets
	 * 
	 * @return
	 */
	public int getSize() {
		return complexParam.size();
	}
}
