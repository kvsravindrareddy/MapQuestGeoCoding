package com.ravindra.geocoding.service.standard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ravindra.geocoding.service.IResponse;
import com.ravindra.geocoding.service.request.GeocodeRequest;
import com.ravindra.geocoding.service.request.GeocodeRequestBuilder;
import com.ravindra.geocoding.service.request.ReverseGeocodeRequest;
import com.ravindra.geocoding.service.request.ReverseGeocodeRequestBuilder;
import com.ravindra.geocoding.service.response.Location;
import com.ravindra.geocoding.service.response.Result;

public class StandardGeocodingServiceTestCase {

	/**
	 * NOTE: The api key presented in the map quest api key manager is url
	 * encoded, it needs to be decoded here.
	 */
	protected static final String KEY = "fGrxrJqv3MSUucNw87g6dPC7hscRq5ms";

	protected StandardGeocodingService service;

	@Before
	public void setUp() throws Exception {
		service = new StandardGeocodingService();
	}

	@Test
	public void testGeocode_CalgaryAddress() throws Exception {
		// setup
		GeocodeRequestBuilder builder = new GeocodeRequestBuilder();
		GeocodeRequest request = builder.key(KEY).location("Marathahalli,Bangalore,Karnataka").build();
		// execute
		IResponse response = service.geocode(request);
		
		Result[] resArr = response.getResults();
		for(Result res : resArr) {
			Location[] locArr = res.getLocations();
			for(Location loc : locArr) {
				System.out.println(loc.getAdminArea1Type()+" : "+loc.getAdminArea1());//Country
				System.out.println(loc.getAdminArea3Type()+" : "+loc.getAdminArea3());//State
				System.out.println(loc.getAdminArea5Type()+" : "+loc.getAdminArea5());//City
				System.out.println("Postal Code : "+loc.getPostalCode());//Postal Code
				System.out.println("Latitude : "+loc.getLatLng().getLat());
				System.out.println("Langtitude : "+loc.getLatLng().getLng());
				System.out.println("GeocodeQualityCode : "+loc.getGeocodeQualityCode());
			}
		}
		
		// verify
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getInfo());
		Assert.assertEquals(new Integer(0), response.getInfo().getStatusCode());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().length);
		Assert.assertNotNull(response.getResults()[0].getLocations());
		Assert.assertEquals(1, response.getResults()[0].getLocations().length);
		Assert.assertEquals("IN", response.getResults()[0].getLocations()[0].getAdminArea1());
	}

	@Test
	public void testReverseGeocode_CalgaryAddress() throws Exception {
		ReverseGeocodeRequestBuilder builder = new ReverseGeocodeRequestBuilder();
		ReverseGeocodeRequest request = builder.key(KEY).location(12.955257, 77.698416).build();

		// execute
		IResponse response = service.reverseGeocode(request);
		System.out.println("---------------------------------------");
		Result[] resArr = response.getResults();
		for(Result res : resArr) {
			Location[] locArr = res.getLocations();
			for(Location loc : locArr) {
				System.out.println(loc.getAdminArea1Type()+" : "+loc.getAdminArea1());//Country
				System.out.println(loc.getAdminArea3Type()+" : "+loc.getAdminArea3());//State
				System.out.println(loc.getAdminArea5Type()+" : "+loc.getAdminArea5());//City
				System.out.println("Postal Code : "+loc.getPostalCode());//Postal Code
				System.out.println("Latitude : "+loc.getLatLng().getLat());
				System.out.println("Langtitude : "+loc.getLatLng().getLng());
				System.out.println("GeocodeQualityCode : "+loc.getGeocodeQualityCode());
			}
		}
		
		// verify
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getInfo());
		Assert.assertEquals(new Integer(0), response.getInfo().getStatusCode());
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().length);
		Assert.assertNotNull(response.getResults()[0].getLocations());
		Assert.assertEquals(1, response.getResults()[0].getLocations().length);
		Assert.assertEquals("IN", response.getResults()[0].getLocations()[0].getAdminArea1());
	}

}
