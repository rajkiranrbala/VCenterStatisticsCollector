package com.vmagent.collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.vmagent.GenericCollector;
import com.vmagent.LogWriter;
import com.vmagent.collectors.CpuStatsCollector.CpuInfo;

public class CpuStatsCollector extends GenericCollector<CpuInfo> {

	private String hostName;

	public CpuStatsCollector(String hostName, LogWriter writer, int sampleTime) {
		super(writer, sampleTime);
		this.hostName = hostName;
	}

	@Override
	public CpuInfo getData() {
		try {
			Process p = Runtime.getRuntime().exec("iostat -c");
			Date d = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
					.getTime();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			reader.readLine();
			reader.readLine();
			reader.readLine();
			String line = reader.readLine();
			String[] stats = line.split("\\s+");
			float usage = 100.0F - Float.parseFloat(stats[6]);
			CpuInfo info = new CpuInfo();
			info.setHostName(hostName);
			info.setTimeStamp(d);
			info.setCpuUsage(usage);

			return info;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public class CpuInfo {
		@SerializedName("hostname")
		private String hostName;
		@SerializedName("timestamp")
		private Date timeStamp;
		@SerializedName("usage")
		private float cpuusage;

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

		public float getCpuUsage() {
			return cpuusage;
		}

		public void setCpuUsage(float usage) {
			this.cpuusage = usage;
		}
	}

}
