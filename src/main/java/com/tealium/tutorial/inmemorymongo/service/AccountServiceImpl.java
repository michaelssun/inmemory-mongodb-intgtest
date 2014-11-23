package com.tealium.tutorial.inmemorymongo.service;

import java.io.IOException;
import java.util.Collection;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class AccountServiceImpl implements AccountService {

	public static final String ID = "_id";
	public static final String DB_NAME = "dbName";
	public static final String COLLECTION_NAME = "collectionName";
	@Inject
	private MongoClient mongoClient;
	@Inject
	@Named(DB_NAME)
	private String dbName;
	@Inject
	@Named(COLLECTION_NAME)
	private String collectionName;

	private ObjectMapper objectMapper;

	@Inject
	public AccountServiceImpl() {
		objectMapper = new ObjectMapper();
	}

	@Override
	public JSONObject getAccount(String name) {
		DBCollection collection = getDBCollection(mongoClient, dbName, collectionName);
		BasicDBObject query = new BasicDBObject("name", name);

		DBObject cursor = collection.findOne(query);
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cursor));
		} catch (JSONException | IOException e) {
			throw new RuntimeException("error to fetch account for name " + name + " -- " + e.getMessage());
		}
		return jsonObject;
	}

	@Override
	public Collection<JSONObject> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAccount(JSONObject jsonobj) throws JSONException {
		DBCollection collection = getDBCollection(mongoClient, dbName, collectionName);
		BasicDBObject dbObj = (BasicDBObject) JSON.parse(jsonobj.toString());
		WriteResult insertData = collection.insert(dbObj, WriteConcern.ACKNOWLEDGED);

		return insertData.getCachedLastError().ok();
	}

	private DBCollection getDBCollection(MongoClient mongoClient, String dbName, String collectionName) {
		return mongoClient.getDB(dbName).getCollection(collectionName);
	}

}
