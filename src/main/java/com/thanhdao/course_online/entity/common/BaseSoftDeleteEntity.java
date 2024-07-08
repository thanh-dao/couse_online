package com.thanhdao.course_online.entity.common;

import com.thanhdao.course_online.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
//@AllArgsConstructor
@Getter@Setter
//@AllArgsConstructor
@MappedSuperclass
public class BaseSoftDeleteEntity extends BaseAuditableEntity<User, UUID> implements Serializable {

    @Column
    private boolean isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
}
