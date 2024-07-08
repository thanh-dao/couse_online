package com.thanhdao.course_online.utils.mapper;

import com.thanhdao.course_online.dto.user.request.RegisterRequest;
import com.thanhdao.course_online.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping()
    RegisterRequest userToRegisterRequest(User user);
//    @Mapping(source = "image", ignore = true)
    User registerRequestToUser(RegisterRequest registerRequest);
}
