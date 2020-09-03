package com.focess.api.task;

import java.util.List;

import com.google.common.collect.Lists;

public class TaskManager implements ITaskManager {

	private List<Task> tasks = Lists.newArrayList();

	public List<Task> getTasks() {
		return Lists.newArrayList(tasks);
	}

	public List<Task> getTaskTimers() {
		return Lists.newArrayList(this.taskTimers);
	}

	private List<Task> taskTimers = Lists.newArrayList();

	public TaskManager() {
	}
	@Override
	public void addTask(Task task) {
		tasks.add(task);
	}
	@Override
	public void addTask(Task task,boolean flag) {
		if (!flag)
			this.addTask(task);
		else this.taskTimers.add(task);
	}
	
	public void removeTask(Task task) {
		this.tasks.remove(task);
	}

}
