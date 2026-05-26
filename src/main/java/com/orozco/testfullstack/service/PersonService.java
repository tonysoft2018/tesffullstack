package com.orozco.testfullstack.service;

import com.orozco.testfullstack.model.ApiResponse;
import com.orozco.testfullstack.model.Person;

public interface PersonService {

    ApiResponse<Person> findAll();

    ApiResponse<Person> findById(Long id);

    ApiResponse<Person> create(Person person);

    ApiResponse<Person> update(Long id, Person person);

    ApiResponse<Person> delete(Long id);
}
