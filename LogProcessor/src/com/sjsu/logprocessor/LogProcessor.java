package com.sjsu.logprocessor;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mysql.jdbc.ResultSet;

public class LogProcessor {

	private Connection sqlConnection;
	private Date timeStamp;
	private MongoAggregationHelper mongoTask;

	public LogProcessor(Date timeStamp) throws UnknownHostException,
			SQLException, ClassNotFoundException {
		Class.forName(Configuration.getConfiguration().getJdbcDriver());
		sqlConnection = DriverManager.getConnection(Configuration
				.getConfiguration().getMySQLUrl(), Configuration
				.getConfiguration().getMySqlUserName(), Configuration
				.getConfiguration().getMySqlPassword());
		this.timeStamp = timeStamp;
		mongoTask = new MongoAggregationHelper(timeStamp);
	}

	public void processVmCpuData() {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertVmCpuInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("cpuinfo", Queries.getCpuInfoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String hostname = (String) obj.get("_id");
				double usage = Double.parseDouble(obj.get("usage").toString());
				pstmt.setTimestamp(1,
						new java.sql.Timestamp(timeStamp.getTime()));
				pstmt.setString(2, hostname);
				pstmt.setDouble(3, usage);
				pstmt.setInt(4, 1);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException s) {
			s.printStackTrace();
		}

	}

	public void processVmMemoryData() {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertVmMemInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("meminfo", Queries.getMemInfoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String hostname = (String) obj.get("_id");
				double total = Double.parseDouble(obj.get("used").toString());
				double used = Double.parseDouble(obj.get("total").toString());
				pstmt.setTimestamp(1,
						new java.sql.Timestamp(timeStamp.getTime()));
				pstmt.setString(2, hostname);
				pstmt.setDouble(3, total);
				pstmt.setDouble(4, used);
				pstmt.setInt(5, 1);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void processVmNetworkData() {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertVmNetInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("netinfo", Queries.getNetInfoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String hostname = ((BasicDBObject) obj.get("_id"))
						.getString("hostname");
				String ninterface = ((BasicDBObject) obj.get("_id"))
						.getString("interface");
				double rx = Double.parseDouble(obj.get("rx").toString());
				double tx = Double.parseDouble(obj.get("tx").toString());
				pstmt.setTimestamp(1,
						new java.sql.Timestamp(timeStamp.getTime()));
				pstmt.setString(2, hostname);
				pstmt.setString(3, ninterface);
				pstmt.setDouble(4, tx);
				pstmt.setDouble(5, rx);
				pstmt.setInt(6, 1);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void procesVmDiskIoData() {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertVmDiskInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("diskio", Queries.getDiskIoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String hostname = ((BasicDBObject) obj.get("_id"))
						.getString("hostname");
				String device = ((BasicDBObject) obj.get("_id"))
						.getString("device");
				double read = Double.parseDouble(obj.get("read").toString());
				double write = Double.parseDouble(obj.get("write").toString());
				pstmt.setTimestamp(1,
						new java.sql.Timestamp(timeStamp.getTime()));
				pstmt.setString(2, hostname);
				pstmt.setString(3, device);
				pstmt.setDouble(4, read);
				pstmt.setDouble(5, write);
				pstmt.setInt(6, 1);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void processVmProcessData() {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertVmCpuInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("procinfo",
							Queries.getProcessInfoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String hostname = (String) obj.get("_id");
				double processes = Double.parseDouble(obj.get("processes")
						.toString());
				double threads = Double.parseDouble(obj.get("threads")
						.toString());
				pstmt.setTimestamp(1,
						new java.sql.Timestamp(timeStamp.getTime()));
				pstmt.setString(2, hostname);
				pstmt.setDouble(3, processes);
				pstmt.setDouble(4, threads);
				pstmt.setInt(5, 1);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// public void insertHostTaskData(AggregationOutput aggregationOutput) {
	//
	// try {
	// Class.forName(Configuration.getConfiguration().getJdbcDriver());
	// con = DriverManager.getConnection(Configuration.getConfiguration()
	// .getMySQLUrl(), Configuration.getConfiguration()
	// .getMySqlUserName(), Configuration.getConfiguration()
	// .getMySqlPassword());
	//
	// PreparedStatement pstmt = null;
	//
	// // Memory data
	// for (DBObject obj : aggregationOutput.results()) {
	// System.out.println(obj);
	//
	// DBObject idobj = (DBObject) obj.get("_id");
	// String target = (String) idobj.get("target");
	// String action = (String) idobj.get("action");
	// Double timeTaken = (Double) obj.get("AvgTimeTaken");
	// // int diskid = (int) obj.get("DiskID");
	//
	// pstmt = con
	// .prepareStatement("INSERT INTO hosttaskinfo (DateTime,targetVM,Action,timeTaken,rollupstamp) VALUES(?, ?, ?,?,5);");
	// pstmt.setTimestamp(1, sqlDate);
	// pstmt.setString(2, target);
	// pstmt.setString(3, action);
	// pstmt.setDouble(4, timeTaken);
	// // pstmt.setInt( 5, diskid);
	// // Add it to the batch
	// System.out.println("in for loop " + pstmt);
	// pstmt.addBatch();
	// pstmt.executeBatch();
	// }
	// // pstmt.executeBatch();
	// System.out.println("1 row affected");
	// con.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// // purgeMongoData("TaskInfo");
	// }

	public void processHostData() {
		try {

			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertHostInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("host", Queries.getHostInfoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String hostname = (String) obj.get("hostname");
				Double cpuUsage = Double.parseDouble(obj.get("cpuusage")
						.toString());
				Double cpuMax = Double
						.parseDouble(obj.get("cpumax").toString());
				Double cpuPercent = Double.parseDouble(obj.get("cpupercentage")
						.toString());
				Double memUsage = Double.parseDouble(obj.get("memusage")
						.toString());
				Double memMax = Double
						.parseDouble(obj.get("memmax").toString());
				Double memPercent = Double.parseDouble(obj.get("mempercentage")
						.toString());
				Double upTime = Double
						.parseDouble(obj.get("uptime").toString());
				Double tx = Double.parseDouble(obj.get("tx").toString());
				Double rx = Double.parseDouble(obj.get("rx").toString());
				pstmt.setTimestamp(1,
						new java.sql.Timestamp(timeStamp.getTime()));
				pstmt.setString(2, hostname);
				pstmt.setDouble(3, cpuUsage);
				pstmt.setDouble(4, cpuMax);
				pstmt.setDouble(5, cpuPercent);
				pstmt.setDouble(6, memUsage);
				pstmt.setDouble(7, memMax);
				pstmt.setDouble(8, memPercent);
				pstmt.setDouble(9, upTime);
				pstmt.setDouble(10, tx);
				pstmt.setDouble(11, rx);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void processHostTaskData() {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlInsertHostTaskInfo());
			AggregationOutput aggregationOutput = mongoTask
					.getAggregatedOutput("task", Queries.getHostTaskInfoQuery());
			for (DBObject obj : aggregationOutput.results()) {
				String timeStamp = (String) obj.get("timestamp");
				String action = (String) obj.get("action");
				String target = (String) obj.get("target");
				double timeTaken = Double.parseDouble(obj.get("timeTaken")
						.toString());

				pstmt.setTimestamp(1, new java.sql.Timestamp(
						MongoAggregationHelper.jsonDateFormat.parse(timeStamp)
								.getTime()));
				pstmt.setString(2, target);
				pstmt.setString(3, action);
				pstmt.setDouble(4, timeTaken);
				pstmt.setInt(5, 1);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException | ParseException s) {
			s.printStackTrace();
		}
	}

	public void rollUpData(Date startTime, Date endTime, int rollupStamp,
			int targetRollupStamp) {
		try {
			PreparedStatement pstmt = sqlConnection.prepareStatement(Queries
					.getSqlExecuteRollUpProcedure());
			pstmt.setTimestamp(1, new Timestamp(timeStamp.getTime()));
			pstmt.setTimestamp(2, new Timestamp(startTime.getTime()));
			pstmt.setTimestamp(3, new Timestamp(endTime.getTime()));
			pstmt.setInt(4, rollupStamp);
			pstmt.setInt(5, targetRollupStamp);
			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void purgeData() {
		mongoTask.purgeData();
	}
}
