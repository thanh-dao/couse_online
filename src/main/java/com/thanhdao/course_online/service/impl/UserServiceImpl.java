package com.thanhdao.course_online.service.impl;

import com.thanhdao.course_online.dto.user.request.RegisterRequest;
import com.thanhdao.course_online.dto.user.response.LoginResponse;
import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.service.RoleService;
import com.thanhdao.course_online.service.common.impl.BaseServiceImpl;
import com.thanhdao.course_online.utils.enums.ExceptionType;
import com.thanhdao.course_online.utils.enums.TokenType;
import com.thanhdao.course_online.utils.enums.Role;
import com.thanhdao.course_online.utils.exceptions.DuplicatedUserInfoException;
import com.thanhdao.course_online.utils.mapper.UserMapper;
import com.thanhdao.course_online.repository.UserRepository;
import com.thanhdao.course_online.service.JwtService;
import com.thanhdao.course_online.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final RoleService roleService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    public long hello() {
        return repository.count();
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        var user = userMapper.registerRequestToUser(registerRequest);
        List<User> duplicatedDataUsers = repository.findAllByEmailOrPhone(registerRequest.email(), registerRequest.phone());
        if(!duplicatedDataUsers.isEmpty()){
            boolean isDuplicatedEmail = false;
            boolean isDuplicatedPhone = false;
            for (User u : duplicatedDataUsers) {
                if (u.getEmail().equals(registerRequest.email())) {
                    isDuplicatedEmail = true;
                }else if (u.getPhone().equals(registerRequest.phone())) {
                    isDuplicatedPhone = true;
                }
            }
            throw new DuplicatedUserInfoException(ExceptionType.DuplicatedUserInfo, "Invalid email or phone number");
        }
        var userRole = roleService.getRole(Role.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
    @Override
    public LoginResponse generateLoginResponse(Authentication authentication){
        var user = (User) authentication.getPrincipal();
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair keyPair = kpg.generateKeyPair();
            var accessToken = jwtService.generateToken(TokenType.ACCESSTOKEN, user, keyPair.getPrivate());
            var refreshToken = jwtService.generateToken(TokenType.REFRESHTOKEN, user, keyPair.getPrivate());
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            String publicKeyStr = Base64.getEncoder().encodeToString(publicKeyBytes);
            user.setPublicKey(publicKeyStr);
            repository.save(user);
            return new LoginResponse(accessToken, refreshToken);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

     }

    @Override
    public void createUsers(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    public List<User> getAll() {
        log.info("Get all users");
        long start = System.currentTimeMillis();
        var users = repository.findAll();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Total time taken: {} ms", elapsedTime);
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return optionalUser.get();
    }

    @Override
    public long count() {
        return repository.count();
    }
}