package com.ysusoft.frame.event.utils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;

public class ActivityUtil {

	/**
	 * 获取案卷编号集合
	 * @param tasks
	 * @return
	 */
	public static List<String> getTaskIds(List<Task> tasks){
		List<String> taskIds = new ArrayList<String>();
		if(tasks.size()>0){
			for(Task task :tasks){
				taskIds.add(task.getId());
			}
		}
		
		return taskIds;
	}
}
