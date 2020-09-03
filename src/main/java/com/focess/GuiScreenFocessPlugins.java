package com.focess;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiScreenFocessPlugins extends GuiScreen {

	private GuiOptions guiOptions;
	protected String screenTitle;
	private GuiFocessPluginsList guiPlugins;

	public GuiScreenFocessPlugins(GuiOptions guiOptions) {
		this.guiOptions = guiOptions;
	}

	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
		this.guiPlugins = new GuiFocessPluginsList(this.mc, this);
		this.screenTitle = "Focess Plugins";
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id == 200)
				this.mc.displayGuiScreen(this.guiOptions);
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.guiPlugins.drawScreen(mouseX, mouseY, partialTicks);
		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 8, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.guiPlugins.handleMouseInput();
	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		boolean flag = true;
		for (GuiFocessPluginEntry entry:guiPlugins.entries)
			if (entry.getButton().mousePressed(mc, mouseX, mouseY)) {
				flag = true;
				entry.getPlugin().setEnable(!entry.getPlugin().isEnable());
				break;
			}
		if (flag)
			super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	protected void mouseReleased(int mouseX, int mouseY, int state) {
		this.guiPlugins.mouseReleased(mouseX, mouseY, state);
		super.mouseReleased(mouseX, mouseY, state);
	}

}
