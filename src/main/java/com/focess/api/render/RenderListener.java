package com.focess.api.render;

import java.util.List;

import com.focess.api.Focess;
import com.focess.api.Pluginable;
import com.focess.core.Plugin;
import com.google.common.collect.Lists;

public abstract class RenderListener implements Pluginable {

	public static class RenderListenerInstance extends RenderListener {

		private List<RenderTask> tasks = Lists.newCopyOnWriteArrayList();

		public RenderListenerInstance() {
			super(Focess.getFocessPlugin());
		}

		@Override
		public void render() {
			for (RenderTask task : tasks) {
				task.render();
				task.reduce();
				if (task.getTime() == 0)
					tasks.remove(task);
			}
		}

	}

	public static abstract class RenderTask {

		private int time;

		public RenderTask(int time) {
			this.time = time;
		}

		public int getTime() {
			return this.time;
		}

		public void reduce() {
			this.time--;
		}

		public abstract void render();
	}

	private static final RenderListenerInstance LISTENER = new RenderListenerInstance();

	static {
		Focess.getRenderManager().addRenderListener(LISTENER);
	}

	private Plugin plugin;

	public RenderListener(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public Plugin getPlugin() {
		return this.plugin;
	}

	public abstract void render();

	public static void addRenderTask(RenderTask task) {
		LISTENER.tasks.add(task);
	}

}
