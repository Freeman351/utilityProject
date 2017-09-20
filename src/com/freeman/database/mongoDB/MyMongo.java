package com.freeman.database.mongoDB;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MyMongo {
	private DB db;
	public DB getDb() {
		return db;
	}

	MyMongo(){
		try {
			Mongo mongo = new Mongo( "localhost" , 27017 );
			System.out.println("connect to MongoDB instant");
			this.db = mongo.getDB("test");
			System.out.println("connect to db (test)");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	MyMongo(String dbHostIp, int dbPort, String dbName){
		try {
			Mongo mongo = new Mongo( dbHostIp , dbPort);
			System.out.println("connect to MongoDB instant==" + dbHostIp + ":" + dbPort);
			this.db = mongo.getDB(dbName);
			System.out.println("connect to db (" + dbName + ")") ;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String []args) throws Exception {
		MyMongo myMongo = new MyMongo( "localhost" , 27017, "test" );
		DB db = myMongo.getDb();
		DBCollection collection = db.getCollection("foo");
		DBObject myDoc = collection.findOne();
		System.out.println(myDoc);
		
		 //Inserting a Document: {"name" : "MongoDB","type" : "database","count" : 1,"info" : {x : 203,y : 10}}

//		 BasicDBObject doc = new BasicDBObject();
//
//		 doc.put("name", "MongoDB");
//		 doc.put("type", "database");
//		 doc.put("count", 1);
//		 BasicDBObject info = new BasicDBObject();
//
//		 info.put("x", 203);
//		 info.put("y", 102);
//
//		 doc.put("info", info);
//		 collection.insert(doc);
		 

		 BasicDBObject query = new BasicDBObject();
		 query.put("info.x", 203);
		 DBCursor cur = collection.find(query);
		 while(cur.hasNext()) {
		     System.out.println(cur.next().toString());
		 }
		 
		 BasicDBObject query1 = new BasicDBObject();
		 query1.put("a", 1);
		 DBObject myDoc1 = collection.findAndRemove(query1);
		 System.out.println("delete first node");
		 cur = collection.find();
		 while(cur.hasNext()) {
		     System.out.println(cur.next());
		 }
	}
}
