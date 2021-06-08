package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    List<Person> findByName(String name);
    List<Person> findByAge(int age);
    List<Person> findByNameAndAge(String name, int age);
    List<Person> findByNameIgnoreCase(String name);
}
