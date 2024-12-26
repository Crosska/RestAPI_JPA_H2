package com.crosska.jpa.JPAWorker.controller;

import com.crosska.jpa.JPAWorker.entity.Person;
import com.crosska.jpa.JPAWorker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h2")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        Person newPerson = personService.savePerson(person);
        if (newPerson != null) {
            return ResponseEntity.ok(newPerson);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/person")
    public ResponseEntity<?> getAllPerson() {
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id) {
        Person person = personService.findById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable int id) {
        if (personService.findById(id) != null) {
            return ResponseEntity.ok(personService.deleteById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
        }
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable long id) {
        if (person != null) {
            if (personService.findById(id) != null) {
                Person updatedPerson = personService.savePerson(person, id);
                if (updatedPerson != null) {
                    return ResponseEntity.ok(updatedPerson);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

}
