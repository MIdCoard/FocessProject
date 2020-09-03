package com.focess.api.network;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;

public class NetworkManager implements INetworkManager {

	private List<PacketListener<?>> listeners = Lists.newArrayList();

	private Minecraft minecraft;

	private NetHandlerPlayClient clientSide;

	public NetworkManager(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public boolean sendServerPacket(Packet<INetHandlerPlayClient> packet) {
		if (this.minecraft.getMyNetworkManager() != null) {
			this.minecraft.getMyNetworkManager().sendPacket(packet);
			return true;
		}
		return false;
	}

	@Override
	public void addPacketListener(PacketListener<?> listener) {
		listeners.add(listener);
	}
	
	public List<PacketListener<?>>	getServerPacketListeners() {
		return Lists.newArrayList(this.listeners);
	}
	@Override
	public boolean sendClientPacket(Packet<INetHandlerPlayServer> packet) {
		if (this.clientSide != null) {
			this.clientSide.sendPacket(packet);
			return true;
		}
		return false;
		
	}

	public void setConnection(NetHandlerPlayClient client) {
		this.clientSide = client;
	}
	
	

}
