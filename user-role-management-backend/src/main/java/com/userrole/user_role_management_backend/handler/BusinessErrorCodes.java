package com.userrole.user_role_management_backend.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BusinessErrorCodes {

    INVALID_ROLE(100, HttpStatus.BAD_REQUEST, "Invalid Role Operation");

    @Getter
    private final int code;
    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final String description;

    BusinessErrorCodes(int code, HttpStatus status, String desc) {
        this.code = code;
        this.httpStatus = status;
        this.description = desc;
    }
}
