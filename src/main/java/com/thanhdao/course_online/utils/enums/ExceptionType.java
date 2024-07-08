package com.thanhdao.course_online.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionType {

    DuplicatedUserInfo("E001", "Duplicated user info", HttpStatus.BAD_REQUEST),
    ;
    private final String code;
    private final String title;
    private final HttpStatus httpStatus;
}
