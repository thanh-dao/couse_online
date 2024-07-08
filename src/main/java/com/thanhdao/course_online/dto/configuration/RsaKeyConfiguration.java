package com.thanhdao.course_online.dto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "variables.rsa")
public record RsaKeyConfiguration(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
