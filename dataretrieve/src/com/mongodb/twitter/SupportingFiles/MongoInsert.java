package com.mongodb.twitter.SupportingFiles;

import com.mongodb.twitter.Configuration.MongodbConf;
import com.mongodb.util.JSON;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import com.mongodb.MongoException;
import com.mongodb.DBObject;
import com.mongodb.DB;






//Mongo Database Configuration
public class MongoInsert {

	public static void performInsertion(String rawValues){
		try {
			Mongo mn = new Mongo(MongodbConf.HOST, MongodbConf.PORT_NUMBER);
			DB database = mn.getDB(MongodbConf.DB_NAME);
			DBCollection coll = database.getCollection(MongodbConf.COLLECTION_NAME);
			DBObject obj = (DBObject) JSON
					.parse(rawValues);
			coll.insert(obj);
		} catch (UnknownHostException exp) {
			exp.printStackTrace();
		} catch (MongoException exp) {
			exp.printStackTrace();
		}
	}
	}


