package com.kosign.todolist.domain;

import com.kosign.todolist.payload.task.TaskResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "task_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private  String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    public TaskResponse toTaskResponse(){
        return new TaskResponse(id,name,description,date,user.getId(),status,category.getId());
    }
}
