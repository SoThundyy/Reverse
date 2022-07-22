package it.thundyy.reverselib.tasks.workload.thread;

import org.bukkit.plugin.java.JavaPlugin;

public class WorkloadThreadInitializer {
    private static final WorkloadThread ASYNC_THREAD = new WorkloadThread();
    private static final WorkloadThread SYNC_THREAD = new WorkloadThread();
    
    private static WorkloadThreadInitializer instance;
    
    public static WorkloadThreadInitializer getInstance() {
        synchronized (WorkloadThreadInitializer.class) {
            if (instance == null) {
                instance = new WorkloadThreadInitializer();
            }
        }
        return instance;
    }
    
    /**
     * Instantiate when the plugin enables.
     *
     * @param plugin the javaplugin instance.
     */
    public void init(JavaPlugin plugin) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, ASYNC_THREAD, 0, 1L);
        plugin.getServer().getScheduler().runTaskTimer(plugin, SYNC_THREAD, 0, 1L);
    }
    
    public WorkloadThread getAsyncThread() {
        return ASYNC_THREAD;
    }
    
    public WorkloadThread getSyncThread() {
        return SYNC_THREAD;
    }
}
