package com.thanhdao.course_online.utils.validators.phone;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.thanhdao.course_online.utils.constants.RegexConstants.PhoneRegex;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PhoneValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@NotBlank
public @interface Phone {
    String regexp() default PhoneRegex;
    String message() default "invalid phone number";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
