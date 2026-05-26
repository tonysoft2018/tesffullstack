package com.orozco.testfullstack.service.impl;

import com.orozco.testfullstack.model.ApiResponse;
import com.orozco.testfullstack.model.Person;
import com.orozco.testfullstack.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person samplePerson;

    @BeforeEach
    void setUp() {
        samplePerson = new Person();
        samplePerson.setId(1L);
        samplePerson.setNombre("Cristian");
        samplePerson.setApellido("Orozco");
        samplePerson.setFechaNacimiento(LocalDate.of(1995, 6, 15));
        samplePerson.setPuesto("Desarrollador Full Stack");
        samplePerson.setSueldo(new BigDecimal("45000.00"));
    }

    @Test
    void findAll_returnsPersonList() {
        when(personRepository.findAll()).thenReturn(List.of(samplePerson));

        ApiResponse<Person> response = personService.findAll();

        assertTrue(response.status);
        assertEquals("Personas obtenidas correctamente", response.msg);
        assertEquals(1, response.data.size());
        assertEquals("Cristian", response.data.get(0).getNombre());
        verify(personRepository).findAll();
    }

    @Test
    void findById_whenExists_returnsPerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(samplePerson));

        ApiResponse<Person> response = personService.findById(1L);

        assertTrue(response.status);
        assertEquals("Persona encontrada", response.msg);
        assertEquals(1L, response.data.get(0).getId());
        verify(personRepository).findById(1L);
    }

    @Test
    void findById_whenNotFound_returnsError() {
        when(personRepository.findById(99L)).thenReturn(Optional.empty());

        ApiResponse<Person> response = personService.findById(99L);

        assertFalse(response.status);
        assertEquals("Persona no encontrada con id: 99", response.msg);
        assertNull(response.data);
    }

    @Test
    void create_persistsAndReturnsCreated() {
        Person request = new Person();
        request.setNombre("Ana");
        request.setApellido("Ruiz");
        request.setFechaNacimiento(LocalDate.of(1992, 8, 10));
        request.setPuesto("QA");
        request.setSueldo(new BigDecimal("32000.00"));

        Person saved = new Person();
        saved.setId(2L);
        saved.setNombre(request.getNombre());
        saved.setApellido(request.getApellido());
        saved.setFechaNacimiento(request.getFechaNacimiento());
        saved.setPuesto(request.getPuesto());
        saved.setSueldo(request.getSueldo());

        when(personRepository.save(request)).thenReturn(saved);

        ApiResponse<Person> response = personService.create(request);

        assertTrue(response.status);
        assertEquals("Persona creada correctamente", response.msg);
        assertEquals(2L, response.data.get(0).getId());
        assertEquals("Ana", response.data.get(0).getNombre());
        verify(personRepository).save(request);
    }

    @Test
    void update_whenExists_updatesFields() {
        Person request = new Person();
        request.setNombre("Cristian");
        request.setApellido("Orozco Martinez");
        request.setFechaNacimiento(LocalDate.of(1995, 6, 15));
        request.setPuesto("Full Stack");
        request.setSueldo(new BigDecimal("52000.00"));

        when(personRepository.findById(1L)).thenReturn(Optional.of(samplePerson));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ApiResponse<Person> response = personService.update(1L, request);

        assertTrue(response.status);
        assertEquals("Persona actualizada correctamente", response.msg);
        assertEquals("Orozco Martinez", response.data.get(0).getApellido());
        assertEquals(new BigDecimal("52000.00"), response.data.get(0).getSueldo());
        verify(personRepository).findById(1L);
        verify(personRepository).save(samplePerson);
    }

    @Test
    void update_whenNotFound_returnsError() {
        Person request = new Person();
        request.setNombre("Test");

        when(personRepository.findById(99L)).thenReturn(Optional.empty());

        ApiResponse<Person> response = personService.update(99L, request);

        assertFalse(response.status);
        assertEquals("Persona no encontrada con id: 99", response.msg);
        assertNull(response.data);
        verify(personRepository, never()).save(any());
    }

    @Test
    void delete_whenExists_removesPerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(samplePerson));

        ApiResponse<Person> response = personService.delete(1L);

        assertTrue(response.status);
        assertEquals("Persona eliminada correctamente", response.msg);
        assertNull(response.data);
        verify(personRepository).findById(1L);
        verify(personRepository).delete(samplePerson);
    }

    @Test
    void delete_whenNotFound_returnsError() {
        when(personRepository.findById(99L)).thenReturn(Optional.empty());

        ApiResponse<Person> response = personService.delete(99L);

        assertFalse(response.status);
        assertEquals("Persona no encontrada con id: 99", response.msg);
        assertNull(response.data);
        verify(personRepository, never()).delete(any());
    }
}
