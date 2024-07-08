package com.thanhdao.course_online.dto.user.request;

import com.thanhdao.course_online.utils.constants.RegexConstants;
import com.thanhdao.course_online.utils.validators.phone.Phone;
import jakarta.validation.constraints.*;

public record RegisterRequest(
//        @Length(min = 5, max = 10)
        @Size(min = 1, max = 30)
        String password,
        @NotBlank
        String fullName,
        @Phone
        String phone,
        @Email(regexp = RegexConstants.EmailRegex)
        @NotBlank
        String email
) {
}
