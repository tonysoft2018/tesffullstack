package com.stefanini.personal.domain.service;

import com.stefanini.personal.domain.dto.PersonDTO;
import com.stefanini.personal.exception.CreatePersonException;
import com.stefanini.personal.exception.NoDataFoundException;
import com.stefanini.personal.exception.PersonNotFoundException;
import com.stefanini.personal.persistance.entitys.Person;
import com.stefanini.personal.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        try {
            Person person = new Person();
            person.setNombre(personDTO.getNombre());
            person.setApellido(personDTO.getApellido());
            person.setFechaNacimiento(personDTO.getFechaNacimiento());
            person.setPuesto(personDTO.getPuesto());
            person.setSueldo(personDTO.getSueldo());

            Person savedPerson = personRepository.save(person);
            return mapToDTO(savedPerson);
        } catch (Exception ex) {
            throw new CreatePersonException("Error al crear la persona: " + ex.getMessage());
        }
    }

    @Override
    public PersonDTO getPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Persona no encontrada con ID: " + id));
        return mapToDTO(person);
    }
    @Override
    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        if (persons.isEmpty()) {
            throw new NoDataFoundException("No se encontraron personas");
        }
        return persons.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO updatePerson(Integer id, PersonDTO personDTO) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        person.setNombre(personDTO.getNombre());
        person.setApellido(personDTO.getApellido());
        person.setFechaNacimiento(personDTO.getFechaNacimiento());
        person.setPuesto(personDTO.getPuesto());
        person.setSueldo(personDTO.getSueldo());
        Person updatedPerson = personRepository.save(person);
        return mapToDTO(updatedPerson);
    }

    @Override
    public void deletePerson(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Persona no encontrada con ID: " + id));
        personRepository.delete(person);
    }

    private PersonDTO mapToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setNombre(person.getNombre());
        personDTO.setApellido(person.getApellido());
        personDTO.setFechaNacimiento(person.getFechaNacimiento());
        personDTO.setPuesto(person.getPuesto());
        personDTO.setSueldo(person.getSueldo());
        return personDTO;
    }
}