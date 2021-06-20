package kz.one.lab.controller;

import kz.one.lab.entity.Person;
import kz.one.lab.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> findAllByName(String name) {
        return personRepository.findAllByName(name);
    }
}
