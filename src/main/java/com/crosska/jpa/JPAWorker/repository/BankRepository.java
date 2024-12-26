package com.crosska.jpa.JPAWorker.repository;

import com.crosska.jpa.JPAWorker.entity.Bank;
import com.crosska.jpa.JPAWorker.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findById(long id);

    <S extends Bank> S save(S Bank);

    Bank findByPerson(Person person);

    Bank deleteById(long id);

}
