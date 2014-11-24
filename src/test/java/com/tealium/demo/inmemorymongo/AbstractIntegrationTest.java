package com.tealium.demo.inmemorymongo;

import junit.framework.TestCase;

import com.google.inject.Provider;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;

public class AbstractIntegrationTest extends TestCase {
	private MongodExecutable _mongodExe;
    private MongodProcess _mongod;

    protected static MongoClient mongoClient;  
    protected static final String DATABASENAME = "mongo_test";
    protected static final String COLLECTIONNAME = "collection_test";
	private static final String PERFERRED_MONGO_DB = "PerferredMongoDB"; 
	private static final String INMEMORY = "inmemory";
    @Override
    protected void setUp() throws Exception {

        MongoDBRuntime runtime = MongoDBRuntime.getDefaultInstance();
        _mongodExe = runtime.prepare(new MongodConfig(Version.V2_0, 12345, false));
        _mongod=_mongodExe.start();

        mongoClient = new MongoClient("localhost", 12345);

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        _mongod.stop();
        _mongodExe.cleanup();
    }

    public Mongo getMongo() {
        return mongoClient;
    }

    public String getDatabaseName() {
        return DATABASENAME;
    }
    
	protected static final class MongoDBInstanceProvider implements Provider<MongoClient> {

		public MongoDBInstanceProvider() {
		}

		public MongoClient get() {
			MongoClient preferredMongoClient = null;
			String preferredMongoDB = System.getProperty(PERFERRED_MONGO_DB);
			if (preferredMongoDB == null || preferredMongoDB.equals(INMEMORY)) {
				preferredMongoClient = mongoClient;
			}else{
				// binding to real mongodb client instance
				throw new IllegalArgumentException("NOT implemented yet");
			}

			return preferredMongoClient;
		}
	}
}
