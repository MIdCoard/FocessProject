package com.focess.api;

import com.focess.core.Plugin;

public class FocessPlugin extends Plugin {
	
	{
		Focess.instance = this;
	}
	
	public boolean isEnable() {
		return true;
	}

	@Override
	public String getName() {
		return "Focess";
	}

	@Override
	public void onEnable() {
	}

}
