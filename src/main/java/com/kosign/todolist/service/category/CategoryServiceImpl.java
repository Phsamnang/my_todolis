package com.kosign.todolist.service.category;
import com.kosign.todolist.domain.Category;
import com.kosign.todolist.domain.User;
import com.kosign.todolist.exception.NotOwnerException;
import com.kosign.todolist.exception.ResourceNotFoundException;
import com.kosign.todolist.payload.category.CategoryRequest;
import com.kosign.todolist.payload.category.CategoryResponse;
import com.kosign.todolist.repository.CategoryRepository;
import com.kosign.todolist.service.Authencition.AuthenticationService;
import com.kosign.todolist.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final AuthenticationService authService;
    private final CategoryRepository cateRepo;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        String email = CurrentUserUtils.getCurrentEmail();
        User user = authService.findUserByEmail(email);
        Category category = cateRepo.save(Category.builder().name(request.name()).user(user).build());
        System.out.printf(String.valueOf(request));
        return category.toCategoryResponse();
    }
    @Override
    public List<CategoryResponse> getAllCategories(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());
        Page<Category> categories = cateRepo.findAll(pageable);
        return categories.stream().map(Category::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return cateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category is not found!")).toCategoryResponse();
    }

    @Override
    public List<CategoryResponse> getAllCategoriesByUser() {
        String email = CurrentUserUtils.getCurrentEmail();
        List<Category> categories = cateRepo.findAllByUser(authService.findUserByEmail(email));
        return categories.stream().map(Category::toCategoryResponse).toList();
    }

    @Override
    public void deleteCategoryById(Long id) {
        String email = CurrentUserUtils.getCurrentEmail();
        User user = authService.findUserByEmail(email);
        cateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category is not found!"));
        Integer deleted = cateRepo.deleteByUserAndId(user, id);
        System.out.println(deleted);
        if (deleted != 1) {
            throw new NotOwnerException("You are not owner this category!");
        }
    }

    @Override
    public void updateCategoryById(Long id, CategoryRequest request) {
        cateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category is not found!"));
        Category category = cateRepo.findByUserAndId(authService.findUserByEmail(CurrentUserUtils.getCurrentEmail()), id);
        if (category == null) {
            throw new NotOwnerException("You are not owner this category!");
        } else {
            category.setName(request.name());
            cateRepo.save(category);
        }
    }
}
