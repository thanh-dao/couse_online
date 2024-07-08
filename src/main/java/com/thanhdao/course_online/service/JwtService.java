package com.thanhdao.course_online.service;

import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.utils.enums.TokenType;
import io.jsonwebtoken.Claims;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface JwtService {
    String generateToken(TokenType tokenType, User user, PrivateKey privateKey) throws NoSuchAlgorithmException;

    Map<String, Object> getClaimsFromToken(TokenType tokenTypeEnum, String token);
    Claims verifyToken(String token, User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
