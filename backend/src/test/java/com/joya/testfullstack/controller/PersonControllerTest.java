package com.joya.testfullstack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joya.testfullstack.model.Person;
import com.joya.testfullstack.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonRepository personRepository;

    private Person samplePerson;

    @BeforeEach
    void setUp() {
        samplePerson = new Person();
        samplePerson.setId(1L);
        samplePerson.setNombre("Cristian");
        samplePerson.setApellido("Joya");
        samplePerson.setFechaNacimiento(LocalDate.of(1995, 6, 15));
        samplePerson.setPuesto("Desarrollador Full Stack");
        samplePerson.setSueldo(new BigDecimal("45000.00"));
    }

    @Test
    void findAll_returnsPersonList() throws Exception {
        when(personRepository.findAll()).thenReturn(List.of(samplePerson));

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Personas obtenidas correctamente"))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].nombre").value("Cristian"));
    }

    @Test
    void findById_whenExists_returnsPerson() throws Exception {
        when(personRepository.findById(1L)).thenReturn(Optional.of(samplePerson));

        mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona encontrada"))
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void findById_whenNotFound_returnsError() throws Exception {
        when(personRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/persons/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.msg").value("Persona no encontrada con id: 99"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    void create_persistsAndReturnsCreated() throws Exception {
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

        when(personRepository.save(any(Person.class))).thenReturn(saved);

        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona creada correctamente"))
                .andExpect(jsonPath("$.data[0].id").value(2))
                .andExpect(jsonPath("$.data[0].nombre").value("Ana"));
    }

    @Test
    void update_whenExists_updatesFields() throws Exception {
        Person request = new Person();
        request.setNombre("Cristian");
        request.setApellido("Joya Martinez");
        request.setFechaNacimiento(LocalDate.of(1995, 6, 15));
        request.setPuesto("Full Stack");
        request.setSueldo(new BigDecimal("52000.00"));

        when(personRepository.findById(1L)).thenReturn(Optional.of(samplePerson));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(put("/api/persons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona actualizada correctamente"))
                .andExpect(jsonPath("$.data[0].apellido").value("Joya Martinez"));
    }

    @Test
    void update_whenNotFound_returnsError() throws Exception {
        Person request = new Person();
        request.setNombre("Test");

        when(personRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/persons/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.msg").value("Persona no encontrada con id: 99"));

        verify(personRepository, never()).save(any());
    }

    @Test
    void delete_whenExists_removesPerson() throws Exception {
        when(personRepository.findById(1L)).thenReturn(Optional.of(samplePerson));

        mockMvc.perform(delete("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona eliminada correctamente"));

        verify(personRepository).delete(samplePerson);
    }

    @Test
    void delete_whenNotFound_returnsError() throws Exception {
        when(personRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/persons/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.msg").value("Persona no encontrada con id: 99"));

        verify(personRepository, never()).delete(any());
    }
}
