package com.kosign.todolist.payload.base;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record BaseError<T>(
        Boolean status,
        Integer code,
        String message,
        LocalDateTime time,
        T error
) {
}
