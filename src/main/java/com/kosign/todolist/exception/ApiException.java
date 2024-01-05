package com.kosign.todolist.exception;

import com.kosign.todolist.payload.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e) {
        return BaseError.builder().message("Information is missing")
                .code(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .status(false)
                .error(e.getFieldError().getDefaultMessage())
                .build();
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseError<?> NotFoundException(ResourceNotFoundException e) {
        return BaseError.builder().message("Information is not found")
                .code(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .status(false)
                .error(e.getMessage())
                .build();
    }
    @ExceptionHandler(NotOwnerException.class)
    public BaseError<?> NotFoundException(NotOwnerException e) {
        return BaseError.builder().message("Not Owner")
                .code(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .status(false)
                .error(e.getMessage())
                .build();
    }

}
