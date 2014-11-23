package com.tealium.tutorial.inmemorymongo.web;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.RuntimeJsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

 
@Singleton
public class AccountRestResourceImpl implements AccountRestResource {

	private final static Logger logger = LoggerFactory.getLogger(AccountRestResourceImpl.class);

	@Inject
	public AccountRestResourceImpl() {
		logger.debug("iN AccountRestResourceImpl");
	}

	@Override
	public Response create(JSONObject inputJsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAccount(String name) {
		JSONObject obj=new JSONObject();
		try {
			obj.put("name", "mike");
		} catch (JSONException e) {
			logger.error(name,e);
			throw new RuntimeJsonMappingException(e.getMessage());
		}
		
		return Response.ok(obj).build();
	}

	@Override
	public Response deleteAccount(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateAccount(String name, JSONObject inputJsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}
