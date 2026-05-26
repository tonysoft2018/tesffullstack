package com.orozco.testfullstack.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiResponseTest {

    @Test
    void ok_buildsSuccessResponse() {
        Person person = new Person();
        person.setId(1L);
        person.setNombre("Cristian");

        ApiResponse<Person> response = ApiResponse.ok("Operacion exitosa", List.of(person));

        assertTrue(response.status);
        assertEquals("Operacion exitosa", response.msg);
        assertEquals(1, response.data.size());
        assertEquals("Cristian", response.data.get(0).getNombre());
    }

    @Test
    void error_buildsFailureResponse() {
        ApiResponse<Person> response = ApiResponse.error("Recurso no encontrado");

        assertFalse(response.status);
        assertEquals("Recurso no encontrado", response.msg);
        assertNull(response.data);
    }
}
