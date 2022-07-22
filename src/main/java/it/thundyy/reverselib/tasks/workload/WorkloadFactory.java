package it.thundyy.reverselib.tasks.workload;

import it.thundyy.reverselib.tasks.workload.thread.WorkloadThreadInitializer;

public class WorkloadFactory {
    
    public static void createWorkload(Workload workload, boolean async) {
        WorkloadThreadInitializer initializer = WorkloadThreadInitializer.getInstance();
        
        if (async) {
            synchronized (initializer.getAsyncThread()) {
                initializer.getAsyncThread().addWorkload(workload);
            }
            return;
        }
        
        initializer.getSyncThread().addWorkload(workload);
    }
    
}
