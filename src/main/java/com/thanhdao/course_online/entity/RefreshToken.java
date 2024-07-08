package com.thanhdao.course_online.entity;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Table
public class RefreshToken extends BaseSoftDeleteEntity {

    @Column
    private String token;
    @Column
    private Date expiredAt;
}
