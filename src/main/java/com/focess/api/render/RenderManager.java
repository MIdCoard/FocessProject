package com.focess.api.render;

import java.util.List;

import com.google.common.collect.Lists;

public class RenderManager implements IRenderManager {
	
	private List<RenderListener> listeners = Lists.newArrayList();
	
	@Override
	public void addRenderListener(RenderListener listener) {
		listeners.add(listener);
	}

	public List<RenderListener> getRenderListeners() {
		return Lists.newArrayList(listeners);
	}
	
	

}
