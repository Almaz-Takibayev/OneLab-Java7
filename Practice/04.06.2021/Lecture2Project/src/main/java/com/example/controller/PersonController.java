package com.example.controller;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
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

    @RequestMapping(value = "/persons/findpersonbyname", method = RequestMethod.GET)
    public List<Person> findByName (@RequestParam String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping(value = "/persons/findpersonbyage", method = RequestMethod.GET)
    public List<Person> findByAge (@RequestParam int age) {
        return personRepository.findByAge(age);
    }

    @RequestMapping(value = "/persons/findpersonsbynameandage", method = RequestMethod.GET)
    public List<Person> findByNameAndAge (@RequestParam String name, @RequestParam int age) {
        return personRepository.findByNameAndAge(name, age);
    }

    @RequestMapping(value = "/persons/ignore", method = RequestMethod.GET)
    public List<Person> findByNameIgnoreCase (@RequestParam String name) {
        return personRepository.findByNameIgnoreCase(name);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> findAll () {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public Person findAll (@PathVariable String id) {
        return personRepository.findById(id).get();
    }
}
