package com.stefanini.personal.domain.service;

import com.stefanini.personal.domain.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    PersonDTO createPerson(PersonDTO personDTO);
    PersonDTO getPersonById(Integer id);
    List<PersonDTO> getAllPersons();
    PersonDTO updatePerson(Integer id, PersonDTO personDTO);
    void deletePerson(Integer id);
}