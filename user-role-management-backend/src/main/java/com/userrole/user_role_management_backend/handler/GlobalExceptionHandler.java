package com.userrole.user_role_management_backend.handler;

import com.userrole.user_role_management_backend.exception.InvalidRoleException;
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

}

