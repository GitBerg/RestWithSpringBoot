package br.com.erudio.restwithspringbootudemy.repository;

import br.com.erudio.restwithspringbootudemy.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}
