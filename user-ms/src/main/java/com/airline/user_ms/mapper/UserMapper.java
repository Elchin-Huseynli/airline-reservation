package com.airline.user_ms.mapper;

import com.airline.user_ms.model.dto.request.UserRequest;
import com.airline.user_ms.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "isEnable", ignore = true),
            @Mapping(target = "tokens", ignore = true),
    })
    User userRequestDtoToUser(UserRequest userRequest);



}
