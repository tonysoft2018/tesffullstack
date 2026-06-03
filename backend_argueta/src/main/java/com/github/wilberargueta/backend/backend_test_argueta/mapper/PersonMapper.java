package com.github.wilberargueta.backend.backend_test_argueta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonCreateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonUpdateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.person.PersonResponseDto;
import com.github.wilberargueta.backend.backend_test_argueta.repository.entity.PersonEntity;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonResponseDto entityToResponse(PersonEntity entity);

    @Mapping(target="id", ignore = true)
    PersonEntity requestToeEntity(PersonCreateDto dto);
    
    PersonEntity requestToeEntity(PersonUpdateDto dto);

}
