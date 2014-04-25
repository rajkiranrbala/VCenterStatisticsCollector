package com.vmagent.collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.annotations.SerializedName;
import com.vmagent.GenericCollector;
import com.vmagent.LogWriter;
import com.vmagent.collectors.MemoryStatsCollector.MemoryInfo;

public class MemoryStatsCollector extends GenericCollector<MemoryInfo> {

	private String hostName;
	private Pattern pattern;

	public MemoryStatsCollector(String hostName, LogWriter writer,
			int sampleTime) {
		super(writer, sampleTime);
		this.hostName = hostName;
		this.pattern = Pattern.compile("(\\d+)");
	}

	@Override
	public MemoryInfo getData() {
		try {
			Process p = Runtime.getRuntime().exec("cat /proc/meminfo");
			Date d = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
					.getTime();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String memTotal = reader.readLine();
			String memFree = reader.readLine();
			reader.close();

			Matcher m = pattern.matcher(memTotal);
			m.find();
			long totalMemory = Long.parseLong(m.group(0));
			m = pattern.matcher(memFree);
			m.find();
			long freeMemory = Long.parseLong(m.group(0));

			MemoryInfo info = new MemoryInfo();
			info.setHostName(hostName);
			info.setTimeStamp(d);
			info.setTotalMemory(totalMemory);
			info.setUsedMemory(totalMemory - freeMemory);
			return info;

		} catch (IOException e) {

		}
		return null;
	}

	public class MemoryInfo {
		@SerializedName("hostname")
		private String hostName;
		@SerializedName("timestamp")
		private Date timeStamp;
		@SerializedName("total")
		private long totalMemory;
		@SerializedName("used")
		private long usedMemory;

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

		public long getTotalMemory() {
			return totalMemory;
		}

		public void setTotalMemory(long totalMemory) {
			this.totalMemory = totalMemory;
		}

		public long getUsedMemory() {
			return usedMemory;
		}

		public void setUsedMemory(long usedMemory) {
			this.usedMemory = usedMemory;
		}
	}

}
