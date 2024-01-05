package com.kosign.todolist.payload.task;

import java.time.LocalDateTime;

public record TaskResponse(Long taskId, String taskName, String description, LocalDateTime date,Long userId,String status,Long categoryId) {
}
