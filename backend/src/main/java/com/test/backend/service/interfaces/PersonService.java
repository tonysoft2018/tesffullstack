package com.test.backend.service.interfaces;
import java.util.List;
import com.test.backend.entity.Person;

public interface PersonService {

    List<Person> findAll();

    Person findById(Integer id);

    Person save(Person person);

    Person update(Integer id, Person person);

    void delete(Integer id);
}
