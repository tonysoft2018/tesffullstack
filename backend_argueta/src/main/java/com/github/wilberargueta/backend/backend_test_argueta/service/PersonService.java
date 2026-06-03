package com.github.wilberargueta.backend.backend_test_argueta.service;

import org.springframework.data.domain.Pageable;

import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonCreateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonUpdateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.PaginatedResponse;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.person.PersonResponseDto;

public interface PersonService {

    PersonResponseDto createPerson(PersonCreateDto createDto);

    PersonResponseDto updatePerson(PersonUpdateDto createDto);

    Boolean deletePerson(Long id);

    PersonResponseDto getPerson(Long id);

    PaginatedResponse<PersonResponseDto> getPerson(Pageable pageable);

}
