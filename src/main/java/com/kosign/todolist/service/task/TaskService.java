package com.kosign.todolist.service.task;

import com.kosign.todolist.payload.task.TaskRequest;
import com.kosign.todolist.payload.task.TaskResponse;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);
}
