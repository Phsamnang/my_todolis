package com.kosign.todolist.controller;
import com.kosign.todolist.payload.base.BaseApi;
import com.kosign.todolist.payload.category.CategoryRequest;
import com.kosign.todolist.payload.category.CategoryResponse;
import com.kosign.todolist.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/category/users")
    public BaseApi<?> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = service.createCategory(request);
        return new BaseApi<>(response, LocalDateTime.now(), true);
    }
    @GetMapping("/categories")
    public BaseApi<?> getAllCategories(@RequestParam(required = false,defaultValue = "0") int pageNo,@RequestParam(required = false,defaultValue = "5") int pageSize) {
        return new BaseApi<>(service.getAllCategories(pageNo,pageSize), LocalDateTime.now(), true);
    }
    @GetMapping("/category/{id}")
    public BaseApi<?>getCategoryById(@PathVariable("id") Long id){
        return new BaseApi<>(service.getCategoryById(id),LocalDateTime.now(),true);
    }
    @GetMapping("/categories/user")
    public BaseApi<?>getAllCategoriesByUser(){
        return new BaseApi<>(service.getAllCategoriesByUser(),LocalDateTime.now(),true);
    }
    @DeleteMapping("/category/{id}/user")
    public BaseApi<?>deleteCategory(@PathVariable("id") Long id){
        service.deleteCategoryById(id);
        return new BaseApi<>("You delete category successfully",LocalDateTime.now(),true);
    }
    @PutMapping("/category/{id}/user")
    public BaseApi<?>updateCategory(@PathVariable("id")Long id,@RequestBody CategoryRequest request){
        service.updateCategoryById(id,request);
        return new BaseApi<>(service.getCategoryById(id),LocalDateTime.now(),true);
    }
}
