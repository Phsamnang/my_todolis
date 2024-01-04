package com.kosign.todolist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Table(name = "category_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @OneToMany(mappedBy = "category")
    private Set<Task> tasks;
}
