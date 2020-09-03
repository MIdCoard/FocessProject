package com.focess.core;

import java.util.List;

import com.focess.api.FocessPlugin;
import com.focess.plugins.TestPlugin;
import com.focess.plugins.TestPlugin2;
import com.google.common.collect.Lists;


public class ClientPlugin {
	
	private static List<Plugin> plugins = Lists.newArrayList();
	
	public static void registerPlugin(Plugin plugin) {
		plugins.add(plugin);
	}
	
	public static List<Plugin> getPlugins() {
		return Lists.newArrayList(plugins);
	}
	
	public static void startListener()  {
		ClientPlugin.registerPlugin(new FocessPlugin());
		ClientPlugin.registerPlugin(new TestPlugin());
		ClientPlugin.registerPlugin(new TestPlugin2());
		for (Plugin plugin:plugins)
			plugin.setEnable();
	}


}
