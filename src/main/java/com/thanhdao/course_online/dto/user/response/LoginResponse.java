package com.thanhdao.course_online.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
