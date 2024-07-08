package com.thanhdao.course_online.repository.common;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T extends BaseSoftDeleteEntity> extends JpaRepository<T, UUID> {

}
