package com.js.springbootstart.exception;

import com.js.springbootstart.pojo.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseMessage exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response){
        // 记录日志
        return new ResponseMessage(500, "error", null);

    }


}
