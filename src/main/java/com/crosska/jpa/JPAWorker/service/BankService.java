package com.crosska.jpa.JPAWorker.service;

import com.crosska.jpa.JPAWorker.entity.Bank;
import com.crosska.jpa.JPAWorker.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank findById(long id) {
        return bankRepository.findById(id);
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    public void delete(long id) {
        bankRepository.deleteById(id);
    }

}
