package com.sjsu.logprocessor;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoAggregationHelper {

	public static final SimpleDateFormat jsonDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	protected DB mongoDb;
	private Date timeStamp;
	private BasicDBObject timeFilterQuery;

	private BasicDBObject timeFilter;

	public MongoAggregationHelper(Date timeStamp) throws UnknownHostException {
		this.timeStamp = timeStamp;
		Mongo m = new Mongo(Configuration.getConfiguration().getMongoDBUrl());
		mongoDb = m.getDB(Configuration.getConfiguration().getMongoDB());
		Calendar cal = Calendar.getInstance();
		cal.setTime(timeStamp);
		cal.add(Calendar.MINUTE, Configuration.getConfiguration()
				.getMongoProcessingInterval());
		Date startTime = cal.getTime();
		BasicDBObject timeConstraint = new BasicDBObject();
		timeConstraint.put("$lt", jsonDateFormat.format(timeStamp));
		timeConstraint.put("$gt", jsonDateFormat.format(startTime));
		timeFilter = new BasicDBObject("timestamp", timeConstraint);
		timeFilterQuery = new BasicDBObject("$match", timeFilter);
	}

	public AggregationOutput getAggregatedOutput(String collectionName,
			BasicDBObject groupQuery) {
		DBCollection collection = mongoDb.getCollection(collectionName);
		AggregationOutput aggregatedData = collection.aggregate(
				timeFilterQuery, groupQuery);
		return aggregatedData;
	}

	public void purgeData() {
		mongoDb.getCollection("cpuinfo").remove(timeFilter);
		mongoDb.getCollection("meminfo").remove(timeFilter);
		mongoDb.getCollection("netinfo").remove(timeFilter);
		mongoDb.getCollection("diskio").remove(timeFilter);
		mongoDb.getCollection("procinfo").remove(timeFilter);
		mongoDb.getCollection("host").remove(timeFilter);
		mongoDb.getCollection("task").remove(timeFilter);

	}

}
