package com.thanhdao.course_online.utils.exceptions;

import com.thanhdao.course_online.utils.enums.ExceptionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter@Setter
//@AllArgsConstructor
//@SuperBuilder

public class ApplicationException extends RuntimeException{
    private String[] descriptions;
    private ExceptionType exceptionType;
    public ApplicationException(ExceptionType exceptionType, String... descriptions) {
        this.exceptionType = exceptionType;
        this.descriptions = descriptions;
    }
}
