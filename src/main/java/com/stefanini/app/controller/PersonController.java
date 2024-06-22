package com.stefanini.app.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.app.dto.PersonListDto;
import com.stefanini.app.dto.PersonRequestDto;
import com.stefanini.app.dto.ResponseDto;
import com.stefanini.app.dto.ResponseGeneralDto;
import com.stefanini.app.service.PersonService;

@CrossOrigin
@RequestMapping("/api/v1")
@RestController
public class PersonController {

    private PersonService personService;
    
    public PersonController(PersonService todoService) {
        this.personService = todoService;
    }
    
    @GetMapping("/persons")
    public ResponseEntity<ResponseDto<PersonListDto>> getAll(){
        ResponseDto<PersonListDto> response = new ResponseDto<>();
        List<PersonListDto> list = personService.getAll();
        
        response.setMessage("OK");
        response.setData(list);
        
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/persons/{id}")
    public ResponseEntity<ResponseGeneralDto<PersonRequestDto>> getById(@PathVariable Integer id){
        ResponseGeneralDto<PersonRequestDto> response = new ResponseGeneralDto<>();
        PersonRequestDto todo = personService.getById(id);
        
        if(Objects.isNull(todo)) {
            response.setMessage("ERROR");
            return ResponseEntity.badRequest().body(response);
        }
        
        response.setMessage("OK");
        response.setData(todo);
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/persons")
    public ResponseEntity<Object> save(@RequestBody PersonRequestDto todoDto) {
        ResponseGeneralDto<PersonRequestDto> response = new ResponseGeneralDto<>();
        PersonRequestDto todo = personService.save(todoDto);
        
        if (Objects.isNull(todo)) {
            response.setMessage("ERROR");
            return ResponseEntity.badRequest().body(response);
        }
        
        response.setMessage("OK");
        response.setData(todo);
        return ResponseEntity.ok().body(response);
    }
    
    @PutMapping("/persons/{id}")
    public ResponseEntity<Object> update(@RequestBody PersonRequestDto todoDto, @PathVariable Integer id) {
        ResponseGeneralDto<PersonRequestDto> response = new ResponseGeneralDto<>();
        PersonRequestDto todo = personService.update(todoDto, id);
        
        if (Objects.isNull(todo)) {
            response.setMessage("ERROR");
            return ResponseEntity.badRequest().body(response);
        }
        
        response.setMessage("OK");
        response.setData(todo);
        return ResponseEntity.ok().body(response);
    }
    
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception {
        ResponseGeneralDto<PersonListDto> response = new ResponseGeneralDto<>();
        personService.delete(id);
        
        response.setMessage("OK");
        response.setData(null);
        return ResponseEntity.ok().body(response);
    }

}
