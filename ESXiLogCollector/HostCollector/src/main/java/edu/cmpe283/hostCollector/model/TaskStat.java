package edu.cmpe283.hostCollector.model;

import com.vmware.vim25.mo.Task;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * User: vplociennik
 * Date: 11/22/13
 * Time: 3:08 AM
 */
public class TaskStat {

    private Date timestamp;
    private String action;
    private String target;
    private Date startTime;
    private Date endTime;
    private long timeTaken;
    //private Integer progress;

    public TaskStat(Task task) throws RemoteException {
        timestamp = new Date();
        action = task.getTaskInfo().getName();
        target = task.getTaskInfo().getEntityName();
        startTime = task.getTaskInfo().startTime.getTime();
        endTime = task.getTaskInfo().completeTime == null ? null : task.getTaskInfo().completeTime.getTime();
        //progress = task.getTaskInfo().progress == null ? 100 : task.getTaskInfo().progress;
        timeTaken = endTime != null ? endTime.getTime() - startTime.getTime() : 0;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskStat taskStat = (TaskStat) o;

        if (action != null ? !action.equals(taskStat.action) : taskStat.action != null) return false;
        if (endTime != null ? !endTime.equals(taskStat.endTime) : taskStat.endTime != null) return false;
        if (startTime != null ? !startTime.equals(taskStat.startTime) : taskStat.startTime != null) return false;
        if (target != null ? !target.equals(taskStat.target) : taskStat.target != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
