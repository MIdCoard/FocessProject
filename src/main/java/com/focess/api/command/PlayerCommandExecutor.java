package com.focess.api.command;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public interface PlayerCommandExecutor extends EntityCommandExecutor {
	
	default void execute(Entity entity,String args[]) {
		if (entity instanceof EntityPlayer) 
			execute((EntityPlayer)entity,args);
	}
	
	void execute(EntityPlayer player,String args[]);

}
