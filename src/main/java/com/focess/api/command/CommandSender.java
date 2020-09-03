package com.focess.api.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandSender {
	
	private ICommandSender sender;

	public CommandSender(ICommandSender sender) {
		this.sender = sender;
	}
	
	public ICommandSender getNMSCommandSender() {
		return this.sender;
	}
	
	public boolean isEntity() {
		return this.sender.getCommandSenderEntity() != null;
	}
	
	public Entity getEntity() {
		return this.sender.getCommandSenderEntity();
	}
	
	public boolean isBlock() {
		return !this.sender.getPosition().equals(BlockPos.ORIGIN);
	}
	
	public boolean isConsole() {
		return this.sender.getName().equals("Rcon");
	}
	
	public BlockPos getPosition() {
		return this.sender.getPosition();
	}
	
	public World getWorld() {
		return this.sender.getEntityWorld();
	}

}
