package com.vmagent.collectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.annotations.SerializedName;
import com.vmagent.GenericCollector;
import com.vmagent.LogWriter;
import com.vmagent.collectors.DiskIoStatsCollector.DiskIoInfo;

public class DiskIoStatsCollector extends GenericCollector<DiskIoInfo[]> {

	private String hostName;

	public DiskIoStatsCollector(String hostName, LogWriter diskioLogWriter,
			int sampleTime) {
		super(diskioLogWriter, sampleTime);
		this.hostName = hostName;
	}

	public class DiskIoInfo {
		@SerializedName("hostname")
		private String hostName;
		@SerializedName("timestamp")
		private Date timeStamp;
		@SerializedName("device")
		private String device;
		@SerializedName("read")
		private float read;
		@SerializedName("write")
		private float write;

		public String getDevice() {
			return device;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public float getRead() {
			return read;
		}

		public void setRead(float read) {
			this.read = read;
		}

		public float getWrite() {
			return write;
		}

		public void setWrite(float write) {
			this.write = write;
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

	@Override
	public DiskIoInfo[] getData() {
		try {
			Process p = Runtime.getRuntime().exec("iostat -d");
			Date d = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
					.getTime();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			reader.readLine();
			reader.readLine();
			reader.readLine();
			String line = reader.readLine();
			ArrayList<DiskIoInfo> list = new ArrayList<DiskIoInfo>();
			while (line != null) {
				try {
					String[] stats = line.split("\\s+");
					DiskIoInfo info = new DiskIoInfo();
					info.setHostName(hostName);
					info.setTimeStamp(d);
					info.setDevice(stats[0]);
					info.setRead(Float.parseFloat(stats[2]));
					info.setWrite(Float.parseFloat(stats[3]));
					list.add(info);
					
				} catch (Exception ex) {
				}
				line = reader.readLine();

			}

			return list.toArray(new DiskIoInfo[] {});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
