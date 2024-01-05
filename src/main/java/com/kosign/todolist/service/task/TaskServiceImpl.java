package com.kosign.todolist.service.task;

import com.kosign.todolist.domain.Category;
import com.kosign.todolist.domain.Task;
import com.kosign.todolist.domain.User;
import com.kosign.todolist.exception.ResourceNotFoundException;
import com.kosign.todolist.payload.task.TaskRequest;
import com.kosign.todolist.payload.task.TaskResponse;
import com.kosign.todolist.repository.CategoryRepository;
import com.kosign.todolist.repository.TaskRepository;
import com.kosign.todolist.service.Authencition.AuthenticationService;
import com.kosign.todolist.service.category.CategoryService;
import com.kosign.todolist.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticationService authService;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        User user = authService.findUserByEmail(CurrentUserUtils.getCurrentEmail());
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(()->new ResourceNotFoundException("Category Id is not found!"));
        return taskRepository.save(
                Task.builder().status(request.status())
                        .name(request.taskName())
                        .description(request.description())
                        .category(category)
                        .date(LocalDateTime.now())
                        .user(user).build()
        ).toTaskResponse();
    }
}
