package com.focess.api.network;

import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;

public interface INetworkManager {

	boolean sendServerPacket(Packet<INetHandlerPlayClient> packet);

	boolean sendClientPacket(Packet<INetHandlerPlayServer> packet);

	void addPacketListener(PacketListener<?> packetListener);

}
