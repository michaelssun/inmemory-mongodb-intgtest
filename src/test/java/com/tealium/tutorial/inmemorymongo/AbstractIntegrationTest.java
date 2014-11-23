package com.tealium.tutorial.inmemorymongo;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import junit.framework.TestCase;

public class AbstractIntegrationTest extends TestCase {
	private MongodExecutable _mongodExe;
    private MongodProcess _mongod;

    protected MongoClient mongoClient; 
    protected static final String DATABASENAME = "mongo_test";
    protected static final String COLLECTIONNAME = "collection_test";

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
}
