package com.kosign.todolist.controller;

import com.kosign.todolist.payload.base.BaseApi;
import com.kosign.todolist.payload.task.TaskRequest;
import com.kosign.todolist.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class TaskController {
    private final TaskService service;
    @PostMapping("/task")
    public BaseApi<?> createTask(@Valid @RequestBody TaskRequest request){
        return new BaseApi<>(service.createTask(request), LocalDateTime.now(),true);
    }
}
