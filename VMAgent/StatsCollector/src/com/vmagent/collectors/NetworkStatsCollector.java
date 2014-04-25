package com.vmagent.collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.google.gson.annotations.SerializedName;
import com.vmagent.GenericCollector;
import com.vmagent.LogWriter;
import com.vmagent.collectors.NetworkStatsCollector.NetInfo;

public class NetworkStatsCollector extends GenericCollector<NetInfo[]> {

	private String hostName;
	private HashMap<String, Info> networkData = new HashMap<String, Info>();

	public NetworkStatsCollector(String hostName, LogWriter writer,
			int sampleTime) throws IOException {
		super(writer, sampleTime);
		this.hostName = hostName;
		networkData = getNetworkData();
	}

	private class Info {
		public long tx;
		public long rx;
		public long timeStamp;
	}

	private HashMap<String, Info> getNetworkData() throws IOException {
		HashMap<String, Info> data = new HashMap<String, Info>();
		Process p = Runtime.getRuntime().exec("cat /proc/net/dev");
		long timeStamp = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		reader.readLine();
		reader.readLine();
		String line = reader.readLine();
		while (line != null) {
			String[] set1 = line.split("\\:");
			if (set1.length == 2) {
				String interfaceName = set1[0].replace(" ", "");
				if (!interfaceName.equals("lo")) {
					String[] fields = set1[1].split("\\s+");
					if (fields.length == 17) {
						long recievedData = Long.parseLong(fields[1]);
						long transmittedData = Long.parseLong(fields[9]);
						Info info = new Info();
						info.timeStamp = timeStamp;
						info.rx = recievedData;
						info.tx = transmittedData;
						data.put(interfaceName, info);
					} else if (fields.length == 16) {
						long recievedData = Long.parseLong(fields[0]);
						long transmittedData = Long.parseLong(fields[8]);
						Info info = new Info();
						info.timeStamp = timeStamp;
						info.rx = recievedData;
						info.tx = transmittedData;
						data.put(interfaceName, info);
					}
				}
			}
			line = reader.readLine();
		}
		return data;
	}

	@Override
	public NetInfo[] getData() {
		try {
			HashMap<String, Info> data = getNetworkData();
			ArrayList<NetInfo> netInfoList = new ArrayList<NetInfo>();
			Date d = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
					.getTime();
			for (String i : data.keySet()) {
				Info info = data.get(i);
				if (networkData.containsKey(i)) {
					Info oldInfo = networkData.get(i);
					float rx = (float) (info.rx - oldInfo.rx)
							/ (float) (info.timeStamp - oldInfo.timeStamp);
					rx = rx * 1000.00F / 1024.00F;
					float tx = (float) (info.tx - oldInfo.tx)
							/ (float) (info.timeStamp - oldInfo.timeStamp);
					tx = tx * 1000.00F / 1024.00F;
					oldInfo.rx = info.rx;
					oldInfo.tx = info.tx;
					oldInfo.timeStamp = info.timeStamp;
					NetInfo netInfo = new NetInfo();
					netInfo.setHostName(hostName);
					netInfo.setNiterface(i);
					netInfo.setTimeStamp(d);
					netInfo.setRx(rx);
					netInfo.setTx(tx);
					netInfoList.add(netInfo);
				} else {
					networkData.put(i, info);
				}
			}
			return netInfoList.toArray(new NetInfo[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public class NetInfo {
		@SerializedName("hostname")
		private String hostName;

		public String getNiterface() {
			return niterface;
		}

		public void setNiterface(String niterface) {
			this.niterface = niterface;
		}

		@SerializedName("timestamp")
		private Date timeStamp;
		@SerializedName("interface")
		private String niterface;
		@SerializedName("tx")
		private float tx;
		@SerializedName("rx")
		private float rx;

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

		public float getTx() {
			return tx;
		}

		public void setTx(float tx) {
			this.tx = tx;
		}

		public float getRx() {
			return rx;
		}

		public void setRx(float rx) {
			this.rx = rx;
		}

	}

}
