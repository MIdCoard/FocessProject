package com.focess.core;

public abstract class Plugin {
	
	private boolean isEnable = false;

	public abstract String getName();
	
	public boolean isEnable() {
		return this.isEnable;
	}
	
	public void setEnable() {
		this.isEnable = true;
		this.onEnable();
	}
	
	public abstract void onEnable();

	public void setEnable(boolean flag) {
		this.isEnable = flag;
	}

}
