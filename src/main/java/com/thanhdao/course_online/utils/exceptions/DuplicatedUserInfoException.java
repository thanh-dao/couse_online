package com.thanhdao.course_online.utils.exceptions;

import com.thanhdao.course_online.utils.enums.ExceptionType;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedUserInfoException extends ApplicationException {

    public DuplicatedUserInfoException(ExceptionType exceptionType, String... messages) {
        super(exceptionType, messages);
    }
}
