package com.example.registry_service.controllerAdviser;

import com.example.registry_service.dto.ErrorModel;
import com.example.registry_service.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviser {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorModel> userExceptionHandler(UserNotFound ex) {
        ErrorModel errorMsg = new ErrorModel(ex.getMessage());
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorModel> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorModel errorModel = new ErrorModel(ex.getMessage());
        return new ResponseEntity<>(errorModel ,  HttpStatus.BAD_REQUEST);
    }
}


