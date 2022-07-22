package it.thundyy.reverselib.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Task extends BukkitRunnable {
    
    public abstract void enable(JavaPlugin plugin);
    
}
