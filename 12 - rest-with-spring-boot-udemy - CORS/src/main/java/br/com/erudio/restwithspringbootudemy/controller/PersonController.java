package br.com.erudio.restwithspringbootudemy.controller;

import br.com.erudio.restwithspringbootudemy.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootudemy.services.personServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@CrossOrigin
@Api(value = "Person EndPoint",description = "Description for person", tags = {"PersonEndPoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private personServices services;

    @ApiOperation(value = "Busca todas as pessoas salvas")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml"})
    public List<PersonVO> findAll() {
        List<PersonVO> persons =  services.findAll();
        persons
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()
                        )
                );
        return persons;
    }

    //@CrossOrigin(value = "http://localhost8080")
    @ApiOperation(value = "Busca uma unica pessoa salva")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id) {

        PersonVO personVO = services.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

   //@CrossOrigin(value = {"http://localhost8080", "http://www.berg.com"})
    @ApiOperation(value = "Cadastra uma pessoa no banco de dados")
    @PostMapping(produces = {"application/json", "application/xml","application/x-yaml"},
    consumes = {"application/json", "application/xml","application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
        return personVO;
    }

    @ApiOperation(value = "Atualiza uma pessoa salva do banco de dados")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = services.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
        return personVO;
    }

    @ApiOperation(value = "Deleta uma pessoa salva do banco de dados")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        services.delete(id);
        return ResponseEntity.ok().build();
    }



}
