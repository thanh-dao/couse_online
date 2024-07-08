package com.thanhdao.course_online.service;

import com.thanhdao.course_online.dto.user.request.RegisterRequest;
import com.thanhdao.course_online.dto.user.response.LoginResponse;
import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.service.common.BaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends BaseService<User>, UserDetailsService {
    long hello();

    void register(RegisterRequest registerRequest);
    LoginResponse generateLoginResponse(Authentication authentication);
    void createUsers(List<User> users);
    List<User> getAll();
}
