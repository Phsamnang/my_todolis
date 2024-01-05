package com.kosign.todolist.service.category;

import com.kosign.todolist.payload.category.CategoryRequest;
import com.kosign.todolist.payload.category.CategoryResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories(int pageNo,int pageSize);
    CategoryResponse getCategoryById(Long id);
    List<CategoryResponse>getAllCategoriesByUser();
    void deleteCategoryById(Long id);
    void updateCategoryById(Long id,CategoryRequest request);
}
