package br.com.erudio.restwithspringbootudemy.controller;

import br.com.erudio.restwithspringbootudemy.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootudemy.services.personServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<PagedModel<PersonVO>> findAll(@RequestParam (value = "page", defaultValue = "0") int page,
                                                        @RequestParam (value = "limit", defaultValue = "12") int limit,
                                                        @RequestParam (value = "direction", defaultValue = "asc") String direction,
                                                        PagedResourcesAssembler assembler) {

        var sortDirection = "desc".equalsIgnoreCase(direction)? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons =  services.findAll(pageable);
        persons
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()
                        )
                );
        return new ResponseEntity<>(assembler.toModel(persons), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca todas as pessoas salvas pelo nome")
    @GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedModel<PersonVO>> findPersonByName(@PathVariable("firstName") String firstName,
                                                                 @RequestParam (value = "page", defaultValue = "0") int page,
                                                                 @RequestParam (value = "limit", defaultValue = "12") int limit,
                                                                 @RequestParam (value = "direction", defaultValue = "asc") String direction,
                                                                 PagedResourcesAssembler assembler) {

        var sortDirection = "desc".equalsIgnoreCase(direction)? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons =  services.findPersonByName(firstName, pageable);
        persons
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()
                        )
                );
        return new ResponseEntity<>(assembler.toModel(persons), HttpStatus.OK);
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

    @ApiOperation(value = "Desabilita uma pessoa através do seu ID")
    @PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
    public PersonVO disablePerson(@PathVariable("id") Long id) {

        PersonVO personVO = services.disablePerson(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @ApiOperation(value = "Deleta uma pessoa salva do banco de dados")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        services.delete(id);
        return ResponseEntity.ok().build();
    }



}
