package com.thanhdao.course_online.entity;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table
@Getter
@Setter
@Entity
//@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseSoftDeleteEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")

    private Set<User> users;

    public Role(com.thanhdao.course_online.utils.enums.Role role){
        this.name = role.name();
    }

    public Role(String name) {
        super();
        this.name = name;
    }
}
