package com.kosign.todolist.repository;

import com.kosign.todolist.domain.Category;
import com.kosign.todolist.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByUser(User user);

    @Modifying
    @Transactional
    Integer deleteByUserAndId(User user,Long id);

    Category findByUserAndId(User user,Long id);
}
