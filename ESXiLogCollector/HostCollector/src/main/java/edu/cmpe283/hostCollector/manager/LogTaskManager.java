package edu.cmpe283.hostCollector.manager;

import com.vmware.vim25.TaskFilterSpec;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.TaskHistoryCollector;
import com.vmware.vim25.mo.TaskManager;
import edu.cmpe283.hostCollector.config.Config;
import edu.cmpe283.hostCollector.model.TaskStat;
import edu.cmpe283.hostCollector.util.GenericCollector;
import edu.cmpe283.hostCollector.util.LogWriter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: vplociennik
 * Date: 11/22/13
 * Time: 3:05 AM
 */
public class LogTaskManager extends GenericCollector<List<TaskStat>> {

    private TaskManager taskManager;
    private LogWriter logWriter;
    private Set<TaskStat> taskSet;

    public LogTaskManager(TaskManager taskManager) throws IOException {
        super(new LogWriter(Config.ROOT+"task.log"), Config.SAMPLE_TIME);
        this.taskManager = taskManager;
        taskSet = new HashSet<TaskStat>();

    }

    @Override
    public List<TaskStat> getData() {
        List<TaskStat> l = new ArrayList<TaskStat>();
        for (Task task : taskManager.getRecentTasks()) {

            try {
               /* System.out.println(task);
                System.out.println(task.getTaskInfo());
                System.out.println(task.getTaskInfo().getName());
                System.out.println(task.getTaskInfo().progress);   */
                if(task.getTaskInfo().getName() != null && task.getTaskInfo().getName().equals("MigrateVM_Task") && task.getTaskInfo().progress == null){
                    try {
                        TaskStat t = new TaskStat(task);
                        if(!taskSet.contains(t)){
                            l.add(t);
                            taskSet.add(t);
                        }
                    } catch (RemoteException e) {

                    }
                }
            } catch (RemoteException e) {

            }
        }

        return l;
    }
}