package kz.one.lab.repository;

import kz.one.lab.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public List<Person> findAllByName(String name);
}
