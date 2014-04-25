package com.sjsu.logprocessor;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration {

	private String mySqlUserName, mySqlPassword, mySQLUrl;
	private int mongoProcessingInterval, firstRollUpTime, secondRollUpTime;
	private boolean purgeData;

	public int getMongoProcessingInterval() {
		return mongoProcessingInterval;
	}

	public void setMongoProcessingInterval(int mongoProcessingInterval) {
		this.mongoProcessingInterval = mongoProcessingInterval;
	}

	public int getFirstRollUpTime() {
		return firstRollUpTime;
	}

	public void setFirstRollUpTime(int firstRollUpTime) {
		this.firstRollUpTime = firstRollUpTime;
	}

	public int getSecondRollUpTime() {
		return secondRollUpTime;
	}

	public void setSecondRollUpTime(int secondRollUpTime) {
		this.secondRollUpTime = secondRollUpTime;
	}

	public String getMySqlUserName() {
		return mySqlUserName;
	}

	public void setMySqlUserName(String mySqlUserName) {
		this.mySqlUserName = mySqlUserName;
	}

	public String getMySqlPassword() {
		return mySqlPassword;
	}

	public void setMySqlPassword(String mySqlPassword) {
		this.mySqlPassword = mySqlPassword;
	}

	public String getMySQLUrl() {
		return mySQLUrl;
	}

	public void setMySQLUrl(String mySQLUrl) {
		this.mySQLUrl = mySQLUrl;
	}

	public String getMongoDBUrl() {
		return mongoDBUrl;
	}

	public void setMongoDBUrl(String mongoDBUrl) {
		this.mongoDBUrl = mongoDBUrl;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	private String mongoDBUrl, mongoDB;

	private String jdbcDriver;

	private static final String CONFIGURATION_FILE = "LogProcessor.config";
	private static Configuration configuration;

	private Configuration() {

	}

	public static Configuration getConfiguration() {
		if (configuration == null) {
			configuration = load();
		}
		return configuration;
	}

	public static void save() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(configuration);
		FileWriter writer;
		try {
			writer = new FileWriter(CONFIGURATION_FILE);
			writer.write(json);
			writer.close();
		} catch (Exception e) {
		}
	}

	public static Configuration load() {
		try {
			Gson gson = new Gson();
			return (Configuration) gson.fromJson(new FileReader(
					CONFIGURATION_FILE), Configuration.class);
		} catch (Exception ex) {
			Configuration configuration = new Configuration();
			configuration.setJdbcDriver("com.mysql.jdbc.Driver");
			configuration.setMongoDBUrl("localhost");
			configuration.setMySQLUrl("jdbc:mysql://127.0.0.1:3306/cmpe283");
			configuration.setMySqlUserName("root");
			configuration.setMySqlPassword("password");
			configuration.setMongoDB("cmpe283");
			configuration.setMongoProcessingInterval(5);
			configuration.setFirstRollUpTime(60);
			configuration.setSecondRollUpTime(24 * 60);
			configuration.setPurgeData(true);
			return configuration;
		}
	}

	public String getMongoDB() {
		return mongoDB;
	}

	public void setMongoDB(String mongoDB) {
		this.mongoDB = mongoDB;
	}

	public boolean isPurgeData() {
		return purgeData;
	}

	public void setPurgeData(boolean purgeData) {
		this.purgeData = purgeData;
	}

}
