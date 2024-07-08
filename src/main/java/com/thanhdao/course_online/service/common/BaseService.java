package com.thanhdao.course_online.service.common;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;

public interface BaseService<T extends BaseSoftDeleteEntity> {
//    void save(T entity, User createdBy);
    long count();
}
