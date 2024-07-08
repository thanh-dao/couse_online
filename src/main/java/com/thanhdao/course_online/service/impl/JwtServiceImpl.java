package com.thanhdao.course_online.service.impl;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.thanhdao.course_online.dto.configuration.JwtConfiguration;
import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.utils.enums.TokenType;
import com.thanhdao.course_online.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtConfiguration jwtConfiguration;

    private String generateToken(Map<String, Object> extraClaims, User user, long expiration, PrivateKey privateKey) throws NoSuchAlgorithmException {
//        extraClaims.put(
//                "role",
//                user.getAuthorities()
//                        .stream().toList()
//                        .get(0).getAuthority()
//                        .replace("ROLE_", "")
//                        .toLowerCase()
//        );

        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(privateKey)
                .compact();
    }

    public String generateToken(TokenType tokenType, User user, PrivateKey privateKey) throws NoSuchAlgorithmException {
        var extraClaims = new HashMap<String, Object>();

        if(TokenType.ACCESSTOKEN == tokenType){
            return generateToken(extraClaims, user, jwtConfiguration.accessTokenExpiration(), privateKey);
        }
        return generateToken(new HashMap<>(), user, jwtConfiguration.refreshTokenExpiration(), privateKey);
    }

    //    public Claims getClaimsFromRefreshToken(String token){
//        return getClaimsFromJWT(token, refreshTokenSecretKey);
//    }
//    public Claims getClaimsFromAccessToken(String token){
//        return getClaimsFromJWT(token, refreshTokenSecretKey);
//    }
    public Map<String, Object> getClaimsFromToken(TokenType tokenTypeEnum, String token)  {
//        JWTParser.parse(token);
//        return Jwts.verifyWith(key);
        Map<String, Object> claims = new HashMap<>();
        JWT jwt = null;
        try {
            jwt = JWTParser.parse(token);

            claims = jwt.getJWTClaimsSet().getClaims();

            for (String key : claims.keySet()) {
                System.out.printf("%s: %s\n", key, claims.get(key));
            }
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return claims;

    }
    public Claims verifyToken(String token, User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicBytes = Base64.getDecoder().decode(user.getPublicKey());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        return Jwts.parser().verifyWith(pubKey).build().parseSignedClaims(token).getPayload();
    }
}
