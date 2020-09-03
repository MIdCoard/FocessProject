package com.focess.api.event;

import java.util.List;

import com.focess.api.Pluginable;
import com.focess.core.Plugin;
import com.google.common.collect.Lists;

import net.minecraft.world.chunk.Chunk;

public abstract class ChunkListener implements Pluginable {
	
	
	private static List<ChunkListener> listeners = Lists.newArrayList();
	
	private Plugin plugin;

	public ChunkListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public Plugin getPlugin() {
		return this.plugin;
	}
	
	public abstract void execute(Chunk chunk);
	
	public static List<ChunkListener> getChunkListeners() {
		return Lists.newArrayList(listeners);
	}
	
	public static void addChunkListener(ChunkListener listener) {
		listeners.add(listener);
	}

}
