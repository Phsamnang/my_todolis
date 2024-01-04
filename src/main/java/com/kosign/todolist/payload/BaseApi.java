package com.kosign.todolist.payload;

import java.time.LocalDateTime;

public record BaseApi<T>(T payload, LocalDateTime date,boolean success) {
}
