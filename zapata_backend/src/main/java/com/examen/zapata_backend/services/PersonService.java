package com.examen.zapata_backend.services;

import com.examen.zapata_backend.entities.Person;
import com.examen.zapata_backend.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person save(Person person) {
        if (!isValidPerson(person)) {
            return null;
        }
        return personRepository.save(person);
    }

    private boolean isValidPerson(Person person) {
        boolean isValid = true;
        if (person.getNombre() == null) return !isValid;
        if (person.getApellido() == null) return !isValid;
        if (person.getFechaNacimiento() == null) return !isValid;
        if (person.getPuesto() == null) return !isValid;
        if (person.getSueldo() == null) return !isValid;
        return isValid;
    }

    public Person update(Person person, Long id) {
        person.setId(id);
        return save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
