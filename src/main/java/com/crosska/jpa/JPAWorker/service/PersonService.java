package com.crosska.jpa.JPAWorker.service;

import com.crosska.jpa.JPAWorker.entity.Person;
import com.crosska.jpa.JPAWorker.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    PersonRepository personRepositoryImpl;

    @Autowired
    public PersonService(PersonRepository personRepositoryImpl) {
        this.personRepositoryImpl = personRepositoryImpl;
    }

    public Person savePerson(Person person) {
        try {
            return personRepositoryImpl.save(person);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person savePerson(Person person, long id) {
        try {
            if (personRepositoryImpl.findById(id) != null) {
                person.setId(id);
                return personRepositoryImpl.save(person);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Person> findAll() {
        return personRepositoryImpl.findAll();
    }

    public Person findById(long id) {
        return personRepositoryImpl.findById(id);
    }

    public Person deleteById(long id) {
        return personRepositoryImpl.deleteById(id);
    }

    public Person updatePerson(Person person) {
        return personRepositoryImpl.save(person);
    }

}
