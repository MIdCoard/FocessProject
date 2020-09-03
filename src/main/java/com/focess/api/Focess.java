package com.focess.api;

import com.focess.api.network.INetworkManager;
import com.focess.api.network.NetworkManager;
import com.focess.api.render.IRenderManager;
import com.focess.api.render.RenderManager;
import com.focess.api.task.ITaskManager;
import com.focess.api.task.TaskManager;
import com.focess.core.Plugin;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.World;

public class Focess {

	private static String username;

	private static NetworkManager networkManager;

	private static TaskManager taskManager;

	private static RenderManager renderManager;

	static FocessPlugin instance;

	public static ITaskManager getTaskManager() {
		return taskManager;
	}

	public static INetworkManager getNetworkManager() {
		return networkManager;
	}

	public static EntityPlayerMP getPlayer() {
		try {
			return getIntegratedServer().getPlayerList().getPlayerByUsername(username);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static World getWorld() {
		return getPlayer().getEntityWorld();
	}

	public static IntegratedServer getIntegratedServer() {
		return getMinecraft().getIntegratedServer();
	}

	public static Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	public static void loadDefault() {
		System.out.println("FocessAPI loaded.");
		networkManager = new NetworkManager(getMinecraft());
		taskManager = new TaskManager();
		renderManager = new RenderManager();
	}

	public static void setUsername(String username) {
		Focess.username = username;
	}

	public static IRenderManager getRenderManager() {
		return renderManager;
	}

	public static Plugin getFocessPlugin() {
		return instance;
	}

}
