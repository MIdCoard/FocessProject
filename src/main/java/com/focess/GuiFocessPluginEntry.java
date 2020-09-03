package com.focess;

import com.focess.core.Plugin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.util.text.TextFormatting;

public class GuiFocessPluginEntry implements  GuiListExtended.IGuiListEntry {

	private Plugin plugin;
	private Minecraft mc;
	
	private GuiButton button;

	public GuiButton getButton() {
		return button;
	}

	public GuiFocessPluginEntry(Plugin plugin,Minecraft mcIn) {
		this.plugin = plugin;
		this.mc = mcIn;
		this.button = new GuiButton(0, 0, 0, 75, 20, "");
	}

	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_) {
		
	}

	@Override
	public void func_192634_a(int p_192634_1_, int p_192634_2_, int p_192634_3_, int p_192634_4_, int p_192634_5_,
			int p_192634_6_, int p_192634_7_, boolean p_192634_8_, float p_192634_9_) {
		this.mc.fontRendererObj.drawString(this.plugin.getName(), mc.currentScreen.width / 2 - 100, p_192634_3_ + p_192634_5_ -mc.fontRendererObj.FONT_HEIGHT - 1, 16777215);
		this.button.xPosition = p_192634_2_ + 190;
		this.button.yPosition = p_192634_3_;
		if (this.plugin.isEnable()) 
			button.displayString =  TextFormatting.GREEN + "Enable";
		else 
			button.displayString =  TextFormatting.RED + "Disable";
        this.button.func_191745_a(mc, p_192634_6_, p_192634_7_, p_192634_9_);
	}

	@Override
	public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
		return false;
	}

	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
		this.button.mouseReleased(x, y);
	}

}
