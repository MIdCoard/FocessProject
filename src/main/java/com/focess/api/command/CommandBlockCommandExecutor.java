package com.focess.api.command;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface CommandBlockCommandExecutor extends BlockCommandExecutor {
	
	default void execute(World world,BlockPos pos,String args[]) {
		IBlockState block = world.getBlockState(pos);
		if (block.getBlock().equals(Blocks.COMMAND_BLOCK) || block.getBlock().equals(Blocks.CHAIN_COMMAND_BLOCK) || block.getBlock().equals(Blocks.REPEATING_COMMAND_BLOCK))
			execute((TileEntityCommandBlock) world.getTileEntity(pos),args);
	}
	
	void execute(TileEntityCommandBlock block,String args[]);

}
