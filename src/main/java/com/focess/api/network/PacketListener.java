package com.focess.api.network;

import com.focess.api.Pluginable;
import com.focess.core.Plugin;

import net.minecraft.network.Packet;

public abstract class PacketListener<T extends Packet<?>> implements Pluginable {
	
	private Plugin plugin;

	public PacketListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public abstract boolean execute(T inPacket);
	
	public Plugin getPlugin() {
		return this.plugin;
	}
}
