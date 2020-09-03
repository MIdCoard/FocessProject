package com.focess.api.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public abstract class Command extends CommandBase {
	
	private static List<Command> commands = Lists.newArrayList();

	private static class Executor {

		private final int count;
		private CommandExecutor executor;
		private final String[] subcommands;

		private Executor(final int count, final String... subcommands) {
			this.subcommands = subcommands;
			this.count = count;
		}

		public Executor addExecutor(final CommandExecutor executor) {
			this.executor = executor;
			return this;
		}

		public boolean checkArgs(final String[] args) {
			for (int i = 0; i < this.subcommands.length; i++)
				if (!this.subcommands[i].equals(args[i]))
					return false;
			return true;
		}

		public boolean checkCount(final int amount) {
			return this.subcommands.length + this.count == amount;
		}

		public void execute(final CommandSender sender, final String[] args) {
			this.executor.execute(sender, args);
		}

		public int getSubCommandsSize() {
			return this.subcommands.length;
		}
	}

	private String name;

	@Override
	public int getRequiredPermissionLevel() {
		return this.level;
	}

	@Override
	public List<String> getCommandAliases() {
		return this.aliases;
	}

	@Override
	public final List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		final List<String> ret = this.tabComplete(new CommandSender(sender), args);
		if (ret == null)
			return Lists.newArrayList();
		if (args == null || args.length == 0)
			return ret;
		else
			return ret.parallelStream().filter(str -> str.startsWith(args[args.length - 1]))
					.collect(Collectors.toList());
	}

	public abstract List<String> tabComplete(CommandSender sender, String args[]);

	private String usage;

	private int level;

	private List<String> aliases;

	private List<Executor> executors = Lists.newArrayList();

	private String invalidMessage;
	
	public static void registerCommand(Command command) {
		commands.add(command);
	}
	
	public static List<Command> getCommands() {
		return Lists.newArrayList(commands);
	}

	public Command(String name, String usage, int level, List<String> aliases, String invalidMessage) {
		this.name = name;
		this.usage = usage;
		this.level = level;
		this.aliases = aliases;
		this.invalidMessage = invalidMessage;
		init();
	}

	@Override
	public String getCommandName() {
		return this.name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return this.usage;
	}

	@Override
	public final void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		int amount = 0;
		if (args != null)
			amount = args.length;
		boolean flag = false;
		for (final Executor executor : this.executors)
			if (executor.checkCount(amount) && executor.checkArgs(args)) {
				executor.execute(new CommandSender(sender),
						Arrays.copyOfRange(args, executor.getSubCommandsSize(), args.length));
				flag = true;
				break;
			}
		if (!flag)
			sender.addChatMessage(ITextComponent.Serializer.jsonToComponent(this.invalidMessage));
	}
	
	
	public abstract void init();
	
	public final Command addConsoleExecutor(final int count, final ConsoleCommandExecutor executor,
			final String... subcommands) {
		this.executors.add(new Executor(count, subcommands).addExecutor(executor));
		return this;
	}

	public final Command addExecutor(final int count, final CommandExecutor executor, final String... subcommands) {
		this.executors.add(new Executor(count, subcommands).addExecutor(executor));
		return this;
	}

	public final Command addPlayerExecutor(final int count, final PlayerCommandExecutor executor,
			final String... subcommands) {
		this.executors.add(new Executor(count, subcommands).addExecutor(executor));
		return this;
	}
	
	public final Command addBlockExecutor(final int count,final BlockCommandExecutor executor,String... subcommands) {
		this.executors.add(new Executor(count, subcommands).addExecutor(executor));
		return this;
	}
	
	public final Command addEntityExecutor(final int count,final EntityCommandExecutor executor,String... subcommands) {
		this.executors.add(new Executor(count, subcommands).addExecutor(executor));
		return this;
	}
	
	public final Command addCommandBlockExecutor(final int count,final CommandBlockCommandExecutor executor,String... subcommands) {
		this.executors.add(new Executor(count, subcommands).addExecutor(executor));
		return this;
	}
	

}
