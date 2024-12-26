package com.crosska.jpa.JPAWorker.controller;

import com.crosska.jpa.JPAWorker.entity.Bank;
import com.crosska.jpa.JPAWorker.repository.BankRepository;
import com.crosska.jpa.JPAWorker.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/h2")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/bank")
    public ResponseEntity<?> addBank(@RequestBody Bank bank) {
        if (bank != null) {
            Bank newBank = bankService.save(bank);
            if (newBank != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(newBank);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/bank")
    public ResponseEntity<?> getAllBanks() {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findAll());
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<?> getBankById(@PathVariable int id) {
        if (bankService.findById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bankService.findById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<?> deleteBankById(@PathVariable int id) {
        if (bankService.findById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/bank/{id}")
    public ResponseEntity<?> updateBankById(@PathVariable int id, @RequestBody Bank bank) {
        if (bank != null) {
            if (bankService.findById(id) != null) {
                return ResponseEntity.status(HttpStatus.OK).body(bankService.save(bank));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
