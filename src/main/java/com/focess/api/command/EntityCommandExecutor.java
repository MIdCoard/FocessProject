package com.focess.api.command;

import net.minecraft.entity.Entity;

public interface EntityCommandExecutor extends CommandExecutor {
	
	default void execute(CommandSender sender,String args[]) {
		if (sender.isEntity())
			execute(sender.getEntity(),args);
	}

	void execute(Entity entity, String[] args);

}
