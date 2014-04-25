package com.vmagent;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import com.vmagent.collectors.CpuStatsCollector;
import com.vmagent.collectors.DiskIoStatsCollector;
import com.vmagent.collectors.MemoryStatsCollector;
import com.vmagent.collectors.NetworkStatsCollector;
import com.vmagent.collectors.ProcessStatsCollector;

public class PerformanceCollector {

	private static String cpuLogFileName = "cpuinfo.log";
	private static String memLogFileName = "meminfo.log";
	private static String diskioLogFileName = "diskio.log";
	private static String netInfoFileName = "netinfo.log";
	private static String processInfoFileName = "procinfo.log";

	private static boolean collectCpuInfo = true;
	private static boolean collectMemInfo = true;
	private static boolean collectDiskioInfo = true;
	private static boolean collectNetInfo = true;
	private static boolean collectProcessInfo = true;

	private static int sampleTime = 5000;

	private static LogWriter memLogWriter;
	private static MemoryStatsCollector memStatsCollector;

	private static LogWriter cpuLogWriter;
	private static CpuStatsCollector cpuStatsCollector;

	private static LogWriter diskioLogWriter;
	private static DiskIoStatsCollector diskioStatsCollector;

	private static LogWriter netLogWriter;
	private static NetworkStatsCollector netStatsCollector;

	private static LogWriter processLogWriter;
	private static ProcessStatsCollector processStatsCollector;

	private static String getHostName() throws SocketException,
			UnknownHostException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface
				.getNetworkInterfaces();
		for (NetworkInterface interface_ : Collections.list(interfaces)) {
			if (interface_.isLoopback())
				continue;
			if (!interface_.isUp())
				continue;
			Enumeration<InetAddress> addresses = interface_.getInetAddresses();
			for (InetAddress address : Collections.list(addresses)) {
				if (address instanceof Inet6Address)
					continue;
				return address.toString();
			}
		}
		return InetAddress.getLocalHost().getHostName();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		OptionParser parser = new OptionParser("cCmMnNdDpPt");
		OptionSet options = parser.parse(args);

		if (options.has("t")) {
			sampleTime = Integer.parseInt(options.valueOf("t").toString());
		}

		if (options.has("c")) {
			if (options.valueOf("c").toString().equals("0")) {
				collectCpuInfo = false;
			}
			if (collectCpuInfo && options.has("C")) {
				cpuLogFileName = (String) options.valueOf("C");
			}
		}

		if (options.has("d")) {
			if (options.valueOf("d").toString().equals("0")) {
				collectDiskioInfo = false;
			}
			if (collectCpuInfo && options.has("D")) {
				diskioLogFileName = (String) options.valueOf("D");
			}
		}

		if (options.has("n")) {
			if (options.valueOf("n").toString().equals("0")) {
				collectNetInfo = false;
			}
			if (collectCpuInfo && options.has("N")) {
				netInfoFileName = (String) options.valueOf("N");
			}
		}

		if (options.has("m")) {
			if (options.valueOf("m").toString().equals("0")) {
				collectMemInfo = false;
			}
			if (collectCpuInfo && options.has("M")) {
				memLogFileName = (String) options.valueOf("M");
			}
		}

		if (options.has("p")) {
			if (options.valueOf("p").toString().equals("0")) {
				collectProcessInfo = false;
			}
			if (collectProcessInfo && options.has("P")) {
				processInfoFileName = (String) options.valueOf("P");
			}
		}

		final String hostname = getHostName().replace("/", "");

		if (collectMemInfo) {
			memLogWriter = new LogWriter(memLogFileName);
			memStatsCollector = new MemoryStatsCollector(hostname,
					memLogWriter, sampleTime);
			memStatsCollector.startCollector();
		}

		if (collectCpuInfo) {
			cpuLogWriter = new LogWriter(cpuLogFileName);
			cpuStatsCollector = new CpuStatsCollector(hostname, cpuLogWriter,
					sampleTime);
			cpuStatsCollector.startCollector();
		}

		if (collectDiskioInfo) {
			diskioLogWriter = new LogWriter(diskioLogFileName);
			diskioStatsCollector = new DiskIoStatsCollector(hostname,
					diskioLogWriter, sampleTime);
			diskioStatsCollector.startCollector();
		}

		if (collectNetInfo) {
			netLogWriter = new LogWriter(netInfoFileName);
			netStatsCollector = new NetworkStatsCollector(hostname,
					netLogWriter, sampleTime);
			netStatsCollector.startCollector();
		}

		if (collectProcessInfo) {
			processLogWriter = new LogWriter(processInfoFileName);
			processStatsCollector = new ProcessStatsCollector(hostname,
					processLogWriter, sampleTime);
			processStatsCollector.startCollector();
		}

	}
}
