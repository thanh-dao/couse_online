package com.thanhdao.course_online.utils.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ExceptionConstant {

    public static final String PropertyValidationErrorCode = "E001";
    public static final String PropertyValidationErrorTitle = "Validation error!";
    public static class User {
        public static class DuplicatedEmail{
            public static final String code = "E200";
            public static final String message = "Your email is already existed in our system! Please enter a new one";
            public static final String  title = "Duplicated email";
        }
        public static class BadCredentials {
            public static final String code = "E201";
            public static final String title = "Invalid Credentials";
            public static final String message = "Login credentials are incorrect, please try again!";
        }

    }
}
