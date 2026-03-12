package com.examen.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.backend.dto.ResponseDTO;
import com.examen.backend.model.Person;
import com.examen.backend.repository.PersonRepository;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @GetMapping
    public ResponseDTO list() {
        return new ResponseDTO(true, "Ok", repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseDTO getById(@PathVariable Integer id) {
        return repo.findById(id)
                .map(p -> new ResponseDTO(true, "Ok", p))
                .orElse(new ResponseDTO(false, "No encontrado", null));
    }

    @PostMapping
    public ResponseDTO create(@RequestBody Person p) {
        return new ResponseDTO(true, "Creado", repo.save(p));
    }

    @PutMapping("/{id}")
    public ResponseDTO update(@PathVariable Integer id, @RequestBody Person p) {

        return repo.findById(id).map(existente -> {
            existente.setNombre(p.getNombre());
            existente.setApellido(p.getApellido());
            existente.setFechaNacimiento(p.getFechaNacimiento());
            existente.setPuesto(p.getPuesto());
            existente.setSueldo(p.getSueldo());
            return new ResponseDTO(true, "Actualizado", repo.save(existente));
        }).orElse(new ResponseDTO(false, "No existe", null));
    }

    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        try {
            repo.deleteById(id);
            return new ResponseDTO(true, "Eliminado correctamente", null);
        } catch (Exception e) {
            return new ResponseDTO(false, "Error al eliminar: " + e.getMessage(), null);
        }
    }
}
