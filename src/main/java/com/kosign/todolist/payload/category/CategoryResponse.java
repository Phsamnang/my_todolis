package com.kosign.todolist.payload.category;

import java.time.LocalDateTime;

public record CategoryResponse(Long categoryId, String categoryName, Long userId, LocalDateTime date) {
}
