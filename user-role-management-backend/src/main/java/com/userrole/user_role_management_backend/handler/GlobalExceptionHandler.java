package com.userrole.user_role_management_backend.handler;

import com.userrole.user_role_management_backend.exception.InvalidRoleException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRole(InvalidRoleException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.INVALID_ROLE.getCode())
                        .businessErrorDescription(BusinessErrorCodes.INVALID_ROLE.getDescription())
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.ROLE_NOT_FOUND.getCode())
                        .businessErrorDescription(BusinessErrorCodes.ROLE_NOT_FOUND.getDescription())
                        .error(ex.getMessage())
                        .build());
    }



}

