package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public List<Person> findAllByName(String name);
    public List<Person> findAllByNameAndAge(String name, int age);
    public List<Person> findAllByAge(int age);
}
