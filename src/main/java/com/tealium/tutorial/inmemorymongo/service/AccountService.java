package com.tealium.tutorial.inmemorymongo.service;

import java.util.Collection;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface AccountService {

	public JSONObject getAccount(String name) throws JSONException;

	public Collection<JSONObject> getAccounts();

	public boolean createAccount(JSONObject jsonobj) throws JSONException;
}
