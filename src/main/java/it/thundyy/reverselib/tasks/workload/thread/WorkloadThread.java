package it.thundyy.reverselib.tasks.workload.thread;

import it.thundyy.reverselib.tasks.workload.Workload;

import java.util.ArrayDeque;
import java.util.Deque;

public class WorkloadThread implements Runnable {
    
    private static final double MAX_MILLIS_PER_TICK = 2.5;
    private static final int MAX_NANOS_PER_TICK = (int) (MAX_MILLIS_PER_TICK * 1E6);
    private final Deque<Workload> workLoadDeque = new ArrayDeque<>();
    
    public void addWorkload(Workload workload) {
        workLoadDeque.add(workload);
    }
    
    
    @Override
    public void run() {
        long stopTime = System.nanoTime() + MAX_NANOS_PER_TICK;
        Workload nextLoad;
        
        while (System.nanoTime() <= stopTime && (nextLoad = this.workLoadDeque.poll()) != null) {
            nextLoad.compute();
        }
    }
}
