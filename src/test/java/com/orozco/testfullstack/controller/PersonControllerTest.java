package com.orozco.testfullstack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orozco.testfullstack.model.ApiResponse;
import com.orozco.testfullstack.model.Person;
import com.orozco.testfullstack.service.PersonService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private PersonService personService;

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
    void findAll_returnsPersonList() throws Exception {
        when(personService.findAll())
                .thenReturn(ApiResponse.ok("Personas obtenidas correctamente", List.of(samplePerson)));

        mockMvc.perform(get("/api/persons/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Personas obtenidas correctamente"))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].nombre").value("Cristian"));
    }

    @Test
    void findById_whenExists_returnsPerson() throws Exception {
        when(personService.findById(1L))
                .thenReturn(ApiResponse.ok("Persona encontrada", List.of(samplePerson)));

        mockMvc.perform(get("/api/persons/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona encontrada"))
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void findById_whenNotFound_returnsError() throws Exception {
        when(personService.findById(99L))
                .thenReturn(ApiResponse.error("Persona no encontrada con id: 99"));

        mockMvc.perform(get("/api/persons/findById/99"))
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

        when(personService.create(any(Person.class)))
                .thenReturn(ApiResponse.ok("Persona creada correctamente", List.of(saved)));

        mockMvc.perform(post("/api/persons/create")
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
        request.setApellido("Orozco Martinez");
        request.setFechaNacimiento(LocalDate.of(1995, 6, 15));
        request.setPuesto("Full Stack");
        request.setSueldo(new BigDecimal("52000.00"));

        Person updated = new Person();
        updated.setId(1L);
        updated.setNombre(request.getNombre());
        updated.setApellido(request.getApellido());
        updated.setFechaNacimiento(request.getFechaNacimiento());
        updated.setPuesto(request.getPuesto());
        updated.setSueldo(request.getSueldo());

        when(personService.update(eq(1L), any(Person.class)))
                .thenReturn(ApiResponse.ok("Persona actualizada correctamente", List.of(updated)));

        mockMvc.perform(put("/api/persons/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona actualizada correctamente"))
                .andExpect(jsonPath("$.data[0].apellido").value("Orozco Martinez"));
    }

    @Test
    void update_whenNotFound_returnsError() throws Exception {
        Person request = new Person();
        request.setNombre("Test");

        when(personService.update(eq(99L), any(Person.class)))
                .thenReturn(ApiResponse.error("Persona no encontrada con id: 99"));

        mockMvc.perform(put("/api/persons/update/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.msg").value("Persona no encontrada con id: 99"));
    }

    @Test
    void delete_whenExists_removesPerson() throws Exception {
        when(personService.delete(1L))
                .thenReturn(ApiResponse.ok("Persona eliminada correctamente", null));

        mockMvc.perform(delete("/api/persons/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.msg").value("Persona eliminada correctamente"));

        verify(personService).delete(1L);
    }

    @Test
    void delete_whenNotFound_returnsError() throws Exception {
        when(personService.delete(99L))
                .thenReturn(ApiResponse.error("Persona no encontrada con id: 99"));

        mockMvc.perform(delete("/api/persons/delete/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.msg").value("Persona no encontrada con id: 99"));
    }
}
