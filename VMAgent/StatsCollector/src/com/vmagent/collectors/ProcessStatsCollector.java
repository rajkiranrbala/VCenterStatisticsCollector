package com.vmagent.collectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.annotations.SerializedName;
import com.vmagent.GenericCollector;
import com.vmagent.LogWriter;
import com.vmagent.collectors.ProcessStatsCollector.ProcessInfo;

public class ProcessStatsCollector extends GenericCollector<ProcessInfo> {

	private String hostName;

	public ProcessStatsCollector(String hostName, LogWriter writer,
			int sampleTime) {
		super(writer, sampleTime);
		this.hostName = hostName;
	}

	@Override
	public ProcessInfo getData() {
		try {
			Process p = Runtime.getRuntime().exec("ps aux");
			Date d = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
					.getTime();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			reader.readLine();
			int processCount = 0;

			String line = reader.readLine();
			while (line != null) {
				processCount++;
				line = reader.readLine();
			}

			p = Runtime.getRuntime().exec("ps -eo nlwp");
			reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			reader.readLine();
			int threadCount = 0;
			line = reader.readLine();
			while (line != null) {
				try {
					threadCount += Integer.parseInt(line.replace(" ", ""));
				} catch (Exception ex) {

				}
				line = reader.readLine();
			}

			ProcessInfo info = new ProcessInfo();
			info.setHostName(hostName);
			info.setTimeStamp(d);
			info.setProcesses(processCount);
			info.setThreads(threadCount);
			return info;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public class ProcessInfo {
		@SerializedName("hostname")
		private String hostName;
		@SerializedName("timestamp")
		private Date timeStamp;
		@SerializedName("processes")
		private int processes;
		@SerializedName("threads")
		private int threads;

		public int getProcesses() {
			return processes;
		}

		public void setProcesses(int processes) {
			this.processes = processes;
		}

		public int getThreads() {
			return threads;
		}

		public void setThreads(int threads) {
			this.threads = threads;
		}

		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			this.hostName = hostName;
		}

		public Date getTimeStamp() {
			return this.timeStamp;
		}

		public void setTimeStamp(Date date) {
			this.timeStamp = date;
		}
	}

}
