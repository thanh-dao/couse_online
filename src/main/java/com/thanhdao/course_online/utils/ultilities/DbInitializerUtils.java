package com.thanhdao.course_online.utils.ultilities;

import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.service.RoleService;
import com.thanhdao.course_online.service.UserService;
import com.thanhdao.course_online.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DbInitializerUtils {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private void initializeRoleData(){
        if(roleService.count() > 0) return;
        var roles = new ArrayList<com.thanhdao.course_online.entity.Role>();
        roles.add(new com.thanhdao.course_online.entity.Role(Role.ROLE_USER));
        roles.add(new com.thanhdao.course_online.entity.Role(Role.ROLE_TEACHER));
        roles.add(new com.thanhdao.course_online.entity.Role(Role.ROLE_ADMIN));
        roleService.createRoles(roles);
    }
    private void initializeUserData(){
        if(userService.count() > 0) return;
        var adminRole = roleService.getRole(Role.ROLE_ADMIN);
        User admin01 = User.builder()
                .email("admin01@gmail.com")
                .password(passwordEncoder.encode("admin1234"))
                .fullName("Quản trị viên 01")
                .phone("012345678")
                .roles(Set.of(adminRole))
                .build();
        User admin02 = User.builder()
                .email("admin02@gmail.com")
                .password(passwordEncoder.encode("admin1234"))
                .fullName("Quản trị viên 02")
                .phone("012345679")
                .roles(Set.of(adminRole))
                .build();
        User admin03 = User.builder()
                .email("admin03@gmail.com")
                .password(passwordEncoder.encode("admin1234"))
                .fullName("Quản trị viên 03")
                .phone("012345680")
                .roles(Set.of(adminRole))
                .build();
        var users = Arrays.asList(
            admin01, admin02, admin03
        );
        userService.createUsers(users);

    }
    @Bean
    public void initializeData(){
        initializeRoleData();
        initializeUserData();
    }

}
