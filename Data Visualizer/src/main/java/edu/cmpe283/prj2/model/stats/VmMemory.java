package edu.cmpe283.prj2.model.stats;

import java.util.Date;

public class VmMemory {
	
	 private Date dateTime;
	    private String hostName;
	    private Double TotalMem;
	    private Double UsedMem;
	    private int  rollupStamp;
	    
	    
	    
		public Date getDateTime() {
			return dateTime;
		}
		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}
		public String getHostName() {
			return hostName;
		}
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		public Double getTotalMem() {
			return TotalMem;
		}
		public void setTotalMem(Double totalMem) {
			TotalMem = totalMem;
		}
		public Double getUsedMem() {
			return UsedMem;
		}
		public void setUsedMem(Double usedMem) {
			UsedMem = usedMem;
		}
		public int  getRollupStamp() {
			return rollupStamp;
		}
		public void setRollupStamp(int  rollupStamp) {
			this.rollupStamp = rollupStamp;
		}
	    
	    


}
