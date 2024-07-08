package com.thanhdao.course_online.repository;

import com.thanhdao.course_online.entity.Role;
import com.thanhdao.course_online.repository.common.BaseRepository;

public interface RoleRepository extends BaseRepository<Role> {
    Role findByName(String name);
}
