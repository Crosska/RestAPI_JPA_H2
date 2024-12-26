package com.crosska.jpa.JPAWorker.repository;

import com.crosska.jpa.JPAWorker.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    <S extends Person> S save(S Person);

    Person deleteById(long id);

}
