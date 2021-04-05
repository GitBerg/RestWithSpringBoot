package br.com.erudio.restwithspringbootudemy.controller;

import br.com.erudio.restwithspringbootudemy.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootudemy.services.bookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Book EndPoint",description = "Description for book", tags = {"BookEndPoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private bookServices services;

    @ApiOperation(value = "Busca todos os livros salvos")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml"})
    public List<BookVO> findAll() {
        List<BookVO> books =  services.findAll();
        books
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()
                        )
                );
        return books;
    }

    @ApiOperation(value = "Busca um unico Livro")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
    public BookVO findById(@PathVariable("id") Long id) {

        BookVO bookVO = services.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    @ApiOperation(value = "Cadastra um livro no banco de dados")
    @PostMapping(produces = {"application/json", "application/xml","application/x-yaml"},
    consumes = {"application/json", "application/xml","application/x-yaml"})
    public BookVO create(@RequestBody BookVO book) {
        BookVO bookVO = services.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @ApiOperation(value = "Atualiza um livro salvo do banco de dados")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO update(@RequestBody BookVO book) {
        BookVO bookVO = services.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @ApiOperation(value = "Deleta um livro salvo do banco de dados")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        services.delete(id);
        return ResponseEntity.ok().build();
    }



}
