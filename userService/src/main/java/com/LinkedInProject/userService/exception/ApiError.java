package com.LinkedInProject.userService.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
public class ApiError {

    private HttpStatus statusCode;
    private String error;
    private LocalDateTime timeStamp;

    public ApiError(){
        this.timeStamp=LocalDateTime.now();
    }
    public ApiError(String error,HttpStatus statusCode){
        this();
        this.error=error;
        this.statusCode=statusCode;
    }
}
