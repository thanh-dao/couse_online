package com.thanhdao.course_online.service.impl;

import com.thanhdao.course_online.repository.RoleRepository;
import com.thanhdao.course_online.service.RoleService;
import com.thanhdao.course_online.service.common.impl.BaseServiceImpl;
import com.thanhdao.course_online.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<com.thanhdao.course_online.entity.Role> implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void createRole(com.thanhdao.course_online.entity.Role role) {
        roleRepository.save(role);

    }

    @Override
    public void createRoles(List<com.thanhdao.course_online.entity.Role> roles) {
        roleRepository.saveAll(roles);
    }

    @Override
    public com.thanhdao.course_online.entity.Role getRole(Role role) {
        return roleRepository.findByName(role.name());
    }

    @Override
    public long count() {
        return roleRepository.count();
    }
}
