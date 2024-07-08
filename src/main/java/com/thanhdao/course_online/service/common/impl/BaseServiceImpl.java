package com.thanhdao.course_online.service.common.impl;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import com.thanhdao.course_online.service.common.BaseService;
import org.springframework.stereotype.Service;

@Service
//@SuperBuilder
//@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseSoftDeleteEntity> implements BaseService<T> {

}
