package com.crosska.jpa.JPAWorker.controller;

import com.crosska.jpa.JPAWorker.entity.Person;
import com.crosska.jpa.JPAWorker.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person controller", description = "Class, working with Persons")
@RestController
@RequestMapping("/h2")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    @Operation(summary = "Add new Person to Database with data from JSON Body",
            description = "Add new Person object to DB and return created Person obj to inspect")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        if (person.getAge() > 0 && person.getAge() < 120) {
            Person newPerson = personService.savePerson(person);
            if (newPerson != null) {
                return ResponseEntity.ok(newPerson);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Wrong Person's age");
        }
    }

    @GetMapping("/person")
    @Operation(summary = "Get all available persons from database",
            description = "Return list of Persons objects which contains in DB")
    public ResponseEntity<?> getAllPerson() {
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/person/{id}")
    @Operation(summary = "Get Person from from database with provided id",
            description = "Get Person object from DB using provided ID, if DB do not contain it, returns null")
    public ResponseEntity<?> getPersonById(@PathVariable int id) {
        Person person = personService.findById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
        }
    }

    @DeleteMapping("/person/{id}")
    @Operation(summary = "Delete Person from database with provided id",
            description = "Delete Person object from DB, with provided ID, if DB do not contain it, returns null")
    public ResponseEntity<?> deletePersonById(@PathVariable int id) {
        if (personService.findById(id) != null) {
            return ResponseEntity.ok(personService.deleteById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
        }
    }

    @PutMapping("/person/{id}")
    @Operation(summary = "Update Person, found by provided id, in database with provided Person parameters",
            description = "Tries to found Person object in DB by provided ID, if found - update Person data, if not - return null")
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
