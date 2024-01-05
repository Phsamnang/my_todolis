package com.kosign.todolist.domain;

import com.kosign.todolist.payload.category.CategoryResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Table(name = "category_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @CreationTimestamp
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @OneToMany(mappedBy = "category")
    private Set<Task> tasks;

    public CategoryResponse toCategoryResponse(){
        return new CategoryResponse(id,name, user.getId(), date);
    }

}
