package com.stefanini.app.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.stefanini.app.dto.PersonListDto;
import com.stefanini.app.dto.PersonRequestDto;
import com.stefanini.app.entity.Person;
import com.stefanini.app.repository.PersonRepository;
import com.stefanini.app.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @Override
    public PersonRequestDto save(PersonRequestDto todoDto) {
        if(!Objects.isNull(todoDto)) {
            Person todo = this.generatePerson(todoDto);
            personRepository.save(todo);
            todoDto.setId(todo.getId());
            return todoDto;
        }else {
            return null;
        }
    }

    @Override
    public PersonRequestDto update(PersonRequestDto personDto, Integer id) {
        Person todo = personRepository.getById(id);
        if(Objects.isNull(todo)) {
            return null;
        }
        
        todo = this.updatePerson(todo, personDto);
        personRepository.save(todo);
        return personDto;

    }
    
    @Override
    public void delete(Integer id) throws Exception {
        Person todo = personRepository.getById(id);
        
        if(Objects.isNull(todo)) {
            throw new Exception("Error, todo no encontrado, verifique por favor");
        }
        
        personRepository.delete(todo);
        
    }

    @Override
    public PersonRequestDto getById(Integer id) {
        return personRepository.getByIdPerson(id);
    }

    @Override
    public List<PersonListDto> getAll() {
        return personRepository.getAll();
    }
    
    private Person generatePerson(PersonRequestDto personDto) {
        Person person = new Person();
        person.setNombre(personDto.getNombre());
        person.setApellido(personDto.getApellido());
        person.setFechaNacimiento(personDto.getFechaNacimiento());
        person.setPuesto(personDto.getPuesto());
        person.setSueldo(personDto.getSueldo());
        return person;
    }
    
    
    private Person updatePerson(Person person, PersonRequestDto personDto) {
        person.setNombre(personDto.getNombre());
        person.setApellido(personDto.getApellido());
        person.setFechaNacimiento(personDto.getFechaNacimiento());
        person.setPuesto(personDto.getPuesto());
        person.setSueldo(personDto.getSueldo());
        return person;
    }
   
}
