package com.kosign.todolist.payload.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Please provide a name")
        String name) {
}
