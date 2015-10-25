package com.ravindra.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.ravindra.geocoding.service.IResponse;
import com.ravindra.geocoding.service.request.GeocodeRequest;
import com.ravindra.geocoding.service.request.GeocodeRequestBuilder;
import com.ravindra.geocoding.service.response.Location;
import com.ravindra.geocoding.service.response.Result;
import com.ravindra.geocoding.service.standard.StandardGeocodingService;

@ManagedBean(name="addrValController")
@RequestScoped
public class AddressValidatorController {

	private static final Logger logger = Logger.getLogger(AddressValidatorController.class);

	private static final String KEY = "fGrxrJqv3MSUucNw87g6dPC7hscRq5ms";

	private String addressLineOne;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String renderResult;
	private boolean showReslt;

	public AddressValidatorController() {

	}

	public AddressValidatorController(String addressLineOne, String street,
			String city, String state, String country, String zipCode,
			String renderResult, String showResult) {
		this.addressLineOne = addressLineOne;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.renderResult = renderResult;
		this.showReslt = showReslt;
	}

	public String geoCodeAddressValidator() {
		showReslt = true;

		StandardGeocodingService geoCodeService = new StandardGeocodingService();
		GeocodeRequestBuilder geoReqBuilder = new GeocodeRequestBuilder();
		GeocodeRequest request = geoReqBuilder.key(KEY).location(this.getStreet()+","+this.getCity()+","+this.getState()+","+this.getCountry()+","+this.getZipCode()).build();
		System.out.println("address.............."+this.getStreet()+","+this.getCity()+","+this.getState()+","+this.getCountry()+","+this.getZipCode());
		logger.debug("address.............."+this.getStreet()+","+this.getCity()+","+this.getState()+","+this.getCountry()+","+this.getZipCode());
		IResponse response = geoCodeService.geocode(request);

		Result[] resArr = response.getResults();
		logger.debug("*********Address found***********"+resArr.length);
		if(resArr.length>0) {
			for(Result res : resArr) {
				Location[] locArr = res.getLocations();
				logger.debug("*********No of locations found**********"+locArr.length);
				for(Location loc : locArr) {
					if(this.getCity().equalsIgnoreCase(loc.getAdminArea5()) && this.getCountry().equalsIgnoreCase(loc.getAdminArea1()) && this.getState().equalsIgnoreCase(loc.getAdminArea3()) && this.getZipCode().equalsIgnoreCase(loc.getPostalCode())) {
						renderResult = "Exact match found for the given address";
						logger.debug("--------Exact match-------------");
						return "";
					} else if((this.getCity().equalsIgnoreCase(loc.getAdminArea5()) || this.getState().equalsIgnoreCase(loc.getAdminArea3())) && this.getCountry().equalsIgnoreCase(loc.getAdminArea1()) && this.getZipCode().equalsIgnoreCase(loc.getPostalCode())) {
						renderResult = "Partial match found";
						if(!this.getCity().equalsIgnoreCase(loc.getAdminArea5())) {
							city = "Please provide valid city : "+loc.getAdminArea5();
						}
						if(!this.getState().equalsIgnoreCase(loc.getAdminArea3())) {
							state = "Please provide valid state : "+loc.getAdminArea3();
						}
						logger.debug("--------Partial match-------------");
						return "";
					}
					logger.debug(loc.getAdminArea1Type()+" : "+loc.getAdminArea1());//Country
					logger.debug(loc.getAdminArea3Type()+" : "+loc.getAdminArea3());//State
					logger.debug(loc.getAdminArea5Type()+" : "+loc.getAdminArea5());//City
					logger.debug("Street : "+loc.getStreet());//Street
					logger.debug("Postal Code : "+loc.getPostalCode());//Postal Code
					logger.debug("Latitude : "+loc.getLatLng().getLat());
					logger.debug("Langtitude : "+loc.getLatLng().getLng());
					logger.debug("GeocodeQualityCode : "+loc.getGeocodeQualityCode());
				}
			}
			//			renderResult = "Multiple address found..........";
			//			return "";
		}
		renderResult = "No Match found for the given zip code and address details... Please enter valid address details";
		return "";
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRenderResult() {
		return renderResult;
	}
	public void setRenderResult(String renderResult) {
		this.renderResult = renderResult;
	}

	public boolean isShowReslt() {
		return showReslt;
	}
	public void setShowReslt(boolean showReslt) {
		this.showReslt = showReslt;
	}

}
