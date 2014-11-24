package com.tealium.demo.inmemorymongo.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.mongodb.MongoClient;
import com.tealium.demo.inmemorymongo.AbstractIntegrationTest;

public class AccountServiceIntegrationTest extends AbstractIntegrationTest {
	private static final String KEY_VALUE2 = "20001010";
	private static final String KEY_NAME2 = "bday";
	private static final String KEY_NAME1 = "name";
	private static final String KEY_VALUE1 = "mike"; 
	private AccountService accountService; 

	@Before
	public void setUp() throws Exception {
		super.setUp();
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(String.class).annotatedWith(Names.named(AccountServiceImpl.DB_NAME)).toInstance(DATABASENAME);
				bind(String.class).annotatedWith(Names.named(AccountServiceImpl.COLLECTION_NAME)).toInstance(COLLECTIONNAME);
				bind(MongoClient.class).toProvider(AbstractIntegrationTest.MongoDBInstanceProvider.class);
				bind(AccountService.class).to(AccountServiceImpl.class);
			}
		}); 

		accountService = injector.getInstance(AccountService.class);
	}

	@Test
	public void testCreateAccount() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(KEY_NAME1, KEY_VALUE1);
		json.put(KEY_NAME2, KEY_VALUE2);

		boolean result = accountService.createAccount(json);
		assertTrue(result);

		JSONObject json1 = accountService.getAccount(KEY_VALUE1);
		assertTrue(json1.getString(KEY_NAME1) != null);
		assertTrue("should be " + json1.getString(KEY_NAME1), json1.getString(KEY_NAME1).equals(KEY_VALUE1));
		assertTrue("should be " + json1.getString(KEY_NAME2), json1.getString(KEY_NAME2).equals(KEY_VALUE2));
	}

	@After
	public void after() {
		accountService = null;
	}

}
