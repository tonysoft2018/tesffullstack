package com.github.wilberargueta.backend.backend_test_argueta.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonCreateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonUpdateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.PaginatedResponse;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.person.PersonResponseDto;
import com.github.wilberargueta.backend.backend_test_argueta.exceptions.NotFoundException;
import com.github.wilberargueta.backend.backend_test_argueta.mapper.PersonMapper;
import com.github.wilberargueta.backend.backend_test_argueta.repository.PersonRepository;
import com.github.wilberargueta.backend.backend_test_argueta.repository.entity.PersonEntity;
import com.github.wilberargueta.backend.backend_test_argueta.service.PersonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImplement implements PersonService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponseDto createPerson(PersonCreateDto createDto) {
        log.info("Saving person");
        PersonEntity savedPersonResult = this.personRepository.save(this.personMapper.requestToeEntity(createDto));
        log.info("Saved  person: {}", savedPersonResult.getId());

        return this.personMapper.entityToResponse(savedPersonResult);
    }

    @Override
    public PersonResponseDto updatePerson(PersonUpdateDto updateDto) {
        Optional<PersonEntity> byIdOptional = this.personRepository.findById(updateDto.getId());
        if (!byIdOptional.isPresent()) {
            log.info("Person not found: {}", updateDto.getId());
            throw new NotFoundException("No se encontró a la persona a actualizar");
        }

        log.info("Updating person");
        PersonEntity savedPersonResult = this.personRepository.save(this.personMapper.requestToeEntity(updateDto));
        log.info("Updated  person: {}", savedPersonResult.getId());

        return this.personMapper.entityToResponse(savedPersonResult);
    }

    @Override
    public Boolean deletePerson(Long id) {
        Optional<PersonEntity> byIdOptional = this.personRepository.findById(id);
        if (!byIdOptional.isPresent()) {
            log.info("Person not found: {}", id);
            throw new NotFoundException("No se encontró a la persona a eliminar");
        }

        log.info("Deleting person");
        this.personRepository.deleteById(id);
        log.info("Deleted  person: {}", id);

        return true;
    }

    @Override
    public PersonResponseDto getPerson(Long id) {
        Optional<PersonEntity> byIdOptional = this.personRepository.findById(id);
        if (!byIdOptional.isPresent()) {
            log.info("Person not found: {}", id);
            throw new NotFoundException("No se encontró a la persona a consultar");
        }

        return this.personMapper.entityToResponse(byIdOptional.get());
    }

    @Override
    public PaginatedResponse<PersonResponseDto> getPerson(Pageable pageable) {
        Page<PersonEntity> all = this.personRepository.findAll(pageable);
        log.info("People found size: {}", all.getSize());

        List<PersonResponseDto> collect = all.getContent().stream().map(this.personMapper::entityToResponse)
                .collect(Collectors.toList());
        PaginatedResponse<PersonResponseDto> response = new PaginatedResponse<>();
        response.setData(collect);
        response.setCurrentPage(all.getNumber());
        response.setTotalPages(all.getTotalPages());
        response.setTotalItems(all.getTotalElements());
        response.setPageSize(all.getSize());
        response.setHasNext(all.hasNext());
        response.setHasPrevious(all.hasPrevious());

        return response;
    }

}
