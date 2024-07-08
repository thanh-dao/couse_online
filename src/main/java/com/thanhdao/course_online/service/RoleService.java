package com.thanhdao.course_online.service;

import com.thanhdao.course_online.service.common.BaseService;
import com.thanhdao.course_online.utils.enums.Role;

import java.util.List;

public interface RoleService extends BaseService<com.thanhdao.course_online.entity.Role> {
    void createRole(com.thanhdao.course_online.entity.Role role);
    void createRoles(List<com.thanhdao.course_online.entity.Role> roles);
    com.thanhdao.course_online.entity.Role getRole(Role role);

}
