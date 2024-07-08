package com.thanhdao.course_online.dto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "variables.jwt")
public record JwtConfiguration(
        long refreshTokenExpiration,
        long accessTokenExpiration
) {
}
