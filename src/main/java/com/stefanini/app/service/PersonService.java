package com.stefanini.app.service;

import java.util.List;

import com.stefanini.app.dto.PersonListDto;
import com.stefanini.app.dto.PersonRequestDto;

public interface PersonService {

    PersonRequestDto save(PersonRequestDto todoDto);
    
    PersonRequestDto update(PersonRequestDto todoDto, Integer id);
    
    void delete(Integer id) throws Exception;
    
    PersonRequestDto getById(Integer id);
    
    List<PersonListDto> getAll();
    
}
