package br.com.erudio.restwithspringbootudemy.controller;

import br.com.erudio.restwithspringbootudemy.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootudemy.services.personServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private personServices services;

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml"})
    public List<PersonVO> findAll() {

        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id) {

        return services.findById(id);
    }

    @PostMapping(produces = {"application/json", "application/xml","application/x-yaml"},
    consumes = {"application/json", "application/xml","application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {

        return services.create(person);
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person) {

        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
         services.delete(id);
         return ResponseEntity.ok().build();
    }



}
