package com.ravindra.geocoding.service.request;

import java.util.Map;

import com.ravindra.geocoding.service.IReverseGeocodeRequest;

public class ReverseGeocodeRequest implements IReverseGeocodeRequest {

	protected Map<String, String> parameters;

	protected ReverseGeocodeRequest() {
	}

	public ReverseGeocodeRequest(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public Map<String, String> getParameters() {
		return parameters;
	}

}
