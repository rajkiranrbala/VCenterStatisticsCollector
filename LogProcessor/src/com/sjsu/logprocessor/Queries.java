package com.sjsu.logprocessor;

import com.mongodb.BasicDBObject;

public class Queries {

	private static BasicDBObject cpuInfoQuery, memInfoQuery, diskIoQuery,
			processInfoQuery, netInfoQuery;

	public static BasicDBObject getCpuInfoQuery() {
		return cpuInfoQuery;
	}

	public static BasicDBObject getMemInfoQuery() {
		return memInfoQuery;
	}

	public static BasicDBObject getDiskIoQuery() {
		return diskIoQuery;
	}

	public static BasicDBObject getProcessInfoQuery() {
		return processInfoQuery;
	}

	public static BasicDBObject getNetInfoQuery() {
		return netInfoQuery;
	}

	public static BasicDBObject getHostInfoQuery() {
		return hostInfoQuery;
	}

	public static BasicDBObject getHostTaskInfoQuery() {
		return hostTaskInfoQuery;
	}

	private static BasicDBObject hostInfoQuery, hostTaskInfoQuery;
	private static String sqlInsertVmCpuInfo;
	private static String sqlInsertVmDiskInfo;

	public static String getSqlInsertVmCpuInfo() {
		return sqlInsertVmCpuInfo;
	}

	public static String getSqlInsertVmDiskInfo() {
		return sqlInsertVmDiskInfo;
	}

	public static String getSqlInsertVmMemInfo() {
		return sqlInsertVmMemInfo;
	}

	public static String getSqlInsertVmNetInfo() {
		return sqlInsertVmNetInfo;
	}

	public static String getSqlInsertVmProcessInfo() {
		return sqlInsertVmProcessInfo;
	}

	public static String getSqlInsertHostInfo() {
		return sqlInsertHostInfo;
	}

	private static String sqlInsertVmMemInfo;
	private static String sqlInsertVmNetInfo;
	private static String sqlInsertVmProcessInfo;
	private static String sqlInsertHostInfo;
	private static String sqlInsertHostTaskInfo;
	private static String sqlExecuteRollUpProcedure;

	public static String getSqlInsertHostTaskInfo() {
		return sqlInsertHostTaskInfo;
	}

	static {
		cpuInfoQuery = new BasicDBObject("$group", new BasicDBObject("_id",
				"hostname").append("usage", new BasicDBObject("$avg", "usage")));

		memInfoQuery = new BasicDBObject("$group", new BasicDBObject("_id",
				"hostname").append("used", new BasicDBObject("$avg", "$used"))
				.append("total", new BasicDBObject("$max", "$total")));

		processInfoQuery = new BasicDBObject("$group", new BasicDBObject("_id",
				"hostname").append("processes",
				new BasicDBObject("$avg", "$processes")).append("threads",
				new BasicDBObject("$avg", "$threads")));

		diskIoQuery = new BasicDBObject("$group", new BasicDBObject("_id",
				new BasicDBObject("hostname", "$hostname").append("device",
						"$device")).append("read",
				new BasicDBObject("$avg", "$read")).append("write",
				new BasicDBObject("$avg", "$write")));

		netInfoQuery = new BasicDBObject("$group", new BasicDBObject("_id",
				new BasicDBObject("hostname", "$hostname").append("interface",
						"$interface")).append("tx",
				new BasicDBObject("$avg", "$tx")).append("rx",
				new BasicDBObject("$avg", "$rx")));

		hostInfoQuery = new BasicDBObject("$group", new BasicDBObject("_id",
				"hostname")
				.append("cpuusage", new BasicDBObject("$avg", "$cpuUSage"))
				.append("cpumax", new BasicDBObject("$max", "$cpuMax"))
				.append("cpuPercentage",
						new BasicDBObject("$avg", "$cpuPercentage"))
				.append("memusage", new BasicDBObject("$avg", "$memUsage"))
				.append("memmax", new BasicDBObject("$max", "$memMax"))
				.append("mempercentage",
						new BasicDBObject("$avg", "$memPercentage"))
				.append("uptime", new BasicDBObject("$max", "$uptime"))
				.append("tx", new BasicDBObject("$avg", "$tx"))
				.append("rx", new BasicDBObject("$avg", "$rx")));

		hostTaskInfoQuery = new BasicDBObject("$project", new BasicDBObject(
				"_id", 0).append("timestamp", 1).append("action", 1)
				.append("target", 1).append("timeTaken", 1));

		sqlInsertVmCpuInfo = "INSERT INTO vmcpuinfo(DateTime, HostName, CPUUsage, RollupStamp) VALUES (?,?,?,?)";
		sqlInsertVmDiskInfo = "INSERT INTO vmdiskinfo(DateTime, HostName, DiskID, WriteIO, ReadIO, RollupStamp) VALUES (?,?,?,?,?,?)";
		sqlInsertVmMemInfo = "INSERT INTO vmmeminfo(DateTime, HostName, TotalMem, UsedMem, RollupStamp) VALUES (?,?,?,?,?)";
		sqlInsertVmNetInfo = "INSERT INTO vmnetinfo(DateTime, HostName, Interface, Tx, Rx, RollupStamp) VALUES (?,?,?,?,?,?)";
		sqlInsertVmProcessInfo = "INSERT INTO vmprocessinfo(DateTime, HostName, Process, Threads, RollupStamp) VALUES (?,?,?,?,?)";
		sqlInsertHostInfo = "INSERT INTO hostinfo(DateTime, hostname, cpuUsage, cpuMax, cpuPercentage, memUsage, memMax, memPercentage, uptime, tx, rx, RollupStamp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		sqlInsertHostTaskInfo = "INSERT INTO hosttaskinfo(DateTime, targetVM, Action, timeTaken, RollupStamp) values(?,?,?,?,?)";
		sqlExecuteRollUpProcedure = "{call rollupdata(?, ?, ? ,?, ?)}";
	}

	public static String getSqlExecuteRollUpProcedure() {
		return sqlExecuteRollUpProcedure;
	}
}
