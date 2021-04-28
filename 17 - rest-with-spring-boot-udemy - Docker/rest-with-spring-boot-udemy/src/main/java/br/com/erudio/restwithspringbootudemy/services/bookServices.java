package br.com.erudio.restwithspringbootudemy.services;

import br.com.erudio.restwithspringbootudemy.converter.DozerConverter;
import br.com.erudio.restwithspringbootudemy.data.model.Book;
import br.com.erudio.restwithspringbootudemy.data.model.Person;
import br.com.erudio.restwithspringbootudemy.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootudemy.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootudemy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookServices {

    @Autowired
    BookRepository repository;


    public BookVO create(BookVO person) {
        var entity = DozerConverter.parseObject(person, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public Page<BookVO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(this::convertToBookVO);
    }

    private BookVO convertToBookVO(Book entity) {
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public BookVO findById(Long id){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, BookVO.class);
    }


    public BookVO update(BookVO book) {
        var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrices(book.getPrices());
        entity.setTitles(book.getTitles());

        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;

    }

    public void delete(Long id){
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }





}
