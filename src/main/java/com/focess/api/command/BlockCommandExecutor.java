package com.focess.api.command;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockCommandExecutor extends CommandExecutor {
	
	void execute(World world,BlockPos pos,String args[]);
	
	default void execute(CommandSender sender,String args[]) {
		if (sender.isBlock())
			execute(sender.getWorld(),sender.getPosition(),args);
	}

}
