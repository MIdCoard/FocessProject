package com.focess.api.task;

import com.focess.api.Pluginable;
import com.focess.core.Plugin;

public abstract class Task implements Pluginable {
	
	private int time;
	private int now;
	private Plugin plugin;
	
	public int getTime() {
		return now;
	}

	public Task(int time,Plugin plugin) {
		this.time = time;
		this.now = time;
		this.plugin = plugin;
	}
	
	public Plugin getPlugin() {
		return plugin;
	}

	public void reduce() {
		this.now--;
	}
	
	public void reset() {
		this.now = time;
	}
	
	public abstract void run();

}
