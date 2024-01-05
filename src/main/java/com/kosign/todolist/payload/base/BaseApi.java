package com.kosign.todolist.payload.base;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record BaseApi<T>(T payload, LocalDateTime date,boolean success) {
}
