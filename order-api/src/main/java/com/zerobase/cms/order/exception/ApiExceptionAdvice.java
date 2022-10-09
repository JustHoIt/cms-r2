package com.zerobase.cms.order.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomException.CustomExceptionResponse> exceptionHandler(HttpServletRequest req,final CustomException customException){
        return  ResponseEntity.status(customException.getStatus())
                .body(CustomException.CustomExceptionResponse.builder()
                        .message(customException.getMessage())
                        .code(customException.getErrorCode().name())
                        .status(customException.getStatus())
                        .build());
    }

}
