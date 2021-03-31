package br.com.erudio.restwithspringbootudemy.services;

import br.com.erudio.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootudemy.model.Person;
import br.com.erudio.restwithspringbootudemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class personServices {

    @Autowired
    PersonRepository repository;


    public Person create(Person person) {

        return repository.save(person);
    }

    public List<Person> findAll(){
        return repository.findAll();
    }
    public Person findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }
    public Person update(Person person) {
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAndress(person.getAndress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }





}
