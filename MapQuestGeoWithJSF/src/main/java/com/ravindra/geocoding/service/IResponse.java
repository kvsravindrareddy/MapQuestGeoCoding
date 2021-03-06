package com.ravindra.geocoding.service;

import com.ravindra.geocoding.service.response.Info;
import com.ravindra.geocoding.service.response.Result;

public interface IResponse {

	/**
	 * Matching results.
	 * 
	 * @return Result[]
	 */
	Result[] getResults();

	/**
	 * This field contains information about the response.
	 * 
	 * The statuscode subfield is an integer return value. Refer to the status
	 * codes page for more details about our status codes and error messages.
	 * 
	 * The messages subfield is an array of error messages that describe the
	 * status
	 * 
	 * @return info
	 */
	Info getInfo();

}
