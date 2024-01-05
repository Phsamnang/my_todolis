package com.kosign.todolist.payload.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRequest(@NotBlank(message = "Task\'s name not be blank") String taskName,
                          String description,
                          @NotBlank String status,
                          @NotNull(message = "Please enter category id") Long categoryId
) {
}
