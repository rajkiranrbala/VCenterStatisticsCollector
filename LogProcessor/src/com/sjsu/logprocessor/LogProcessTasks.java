package com.sjsu.logprocessor;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogProcessTasks {

	public static void main(String args[]) {
		TimerTask mongoTask = new TimerTask() {

			@Override
			public void run() {
				LogProcessor mongoSql;
				try {
					mongoSql = new LogProcessor(Calendar.getInstance()
							.getTime());
					mongoSql.processVmCpuData();
					mongoSql.processVmMemoryData();
					mongoSql.processVmNetworkData();
					mongoSql.processVmProcessData();
					mongoSql.procesVmDiskIoData();
					mongoSql.processHostData();
					mongoSql.processHostTaskData();
					if (Configuration.getConfiguration().isPurgeData()) {
						mongoSql.purgeData();
					}
				} catch (UnknownHostException | ClassNotFoundException
						| SQLException e) {
					e.printStackTrace();
				}
			}
		};
		TimerTask firstRollupTask = new TimerTask() {

			@Override
			public void run() {
				LogProcessor mongoSql;
				try {
					Date endTime = Calendar.getInstance().getTime();
					Calendar cal = Calendar.getInstance();
					cal.setTime(endTime);
					cal.add(Calendar.MINUTE, -1
							* Configuration.getConfiguration()
									.getFirstRollUpTime());
					Date startTime = cal.getTime();
					mongoSql = new LogProcessor(endTime);
					mongoSql.rollUpData(startTime, endTime, 1, 2);
				} catch (UnknownHostException | ClassNotFoundException
						| SQLException e) {
					e.printStackTrace();
				}

			}
		};
		TimerTask secondRollupTask = new TimerTask() {

			@Override
			public void run() {
				LogProcessor mongoSql;
				try {
					Date endTime = Calendar.getInstance().getTime();
					Calendar cal = Calendar.getInstance();
					cal.setTime(endTime);
					cal.add(Calendar.MINUTE, -1
							* Configuration.getConfiguration()
									.getSecondRollUpTime());
					Date startTime = cal.getTime();

					mongoSql = new LogProcessor(endTime);
					mongoSql.rollUpData(startTime, endTime, 2, 3);
				} catch (UnknownHostException | ClassNotFoundException
						| SQLException e) {
					e.printStackTrace();
				}

			}
		};
		Timer tasks = new Timer();
		tasks.schedule(mongoTask, Configuration.getConfiguration()
				.getMongoProcessingInterval(), Configuration.getConfiguration()
				.getMongoProcessingInterval() * 60 * 1000);
		tasks.schedule(firstRollupTask, Configuration.getConfiguration()
				.getFirstRollUpTime(), Configuration.getConfiguration()
				.getFirstRollUpTime() * 60 * 1000);
		tasks.schedule(secondRollupTask, Configuration.getConfiguration()
				.getSecondRollUpTime(), Configuration.getConfiguration()
				.getSecondRollUpTime() * 60 * 1000);
	}
}
