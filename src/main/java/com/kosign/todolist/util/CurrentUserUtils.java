package com.kosign.todolist.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {
    public static String getCurrentEmail(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            return authentication.getName();
        }
        return null;
    }
}
