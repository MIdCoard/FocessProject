package com.focess;

import java.util.List;

import com.focess.core.ClientPlugin;
import com.focess.core.Plugin;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class GuiFocessPluginsList extends GuiListExtended {

	 protected final List<GuiFocessPluginEntry> entries = Lists.newArrayList();
	
	public List<GuiFocessPluginEntry> getEntries() {
		return entries;
	}

	public GuiFocessPluginsList(Minecraft mcIn,GuiScreenFocessPlugins focess) {
		super(mcIn, focess.width + 45, focess.height, 31, focess.height - 64, 20);
		for (Plugin plugin:ClientPlugin.getPlugins())
			entries.add(new GuiFocessPluginEntry(plugin, mcIn));
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return entries.get(index);
	}

	@Override
	protected int getSize() {
		return this.entries.size();
	}

}
