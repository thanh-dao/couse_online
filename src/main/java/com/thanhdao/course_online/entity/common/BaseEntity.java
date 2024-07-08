package com.thanhdao.course_online.entity.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity<PK> {
    @Id
    @GeneratedValue()
    protected PK id;
}
