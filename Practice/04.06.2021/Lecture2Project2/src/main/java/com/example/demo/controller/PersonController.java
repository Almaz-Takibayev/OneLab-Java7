package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.entity.Phone;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public Person save (@RequestParam String name, @RequestParam int age) {
        return personRepository.save(new Person(name, age));
    }

    @RequestMapping(value = "/persons/name", method = RequestMethod.GET)
    public List<Person> findAllByName (@RequestParam String name) {
        return personRepository.findAllByName(name);
    }

    @RequestMapping(value = "/persons/age", method = RequestMethod.GET)
    public List<Person> findAllByAge (@RequestParam int age) {
        return personRepository.findAllByAge(age);
    }

    @RequestMapping(value = "/persons/nameandage", method = RequestMethod.GET)
    public List<Person> findAllByNameAndAge (@RequestParam String name, @RequestParam int age) {
        return personRepository.findAllByNameAndAge(name, age);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> findAll () {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public List<Phone> findAll (@PathVariable Long id) {
        return personRepository.findById(id).get().getPhones();
    }
}
