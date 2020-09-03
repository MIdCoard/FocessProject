package com.focess.api.command;

public interface ConsoleCommandExecutor extends CommandExecutor {
	
	void execute(String args[]);
	
	default void exeacute(CommandSender sender,String args[]) {
		if (sender.isConsole())
			execute(args);
	}

}
