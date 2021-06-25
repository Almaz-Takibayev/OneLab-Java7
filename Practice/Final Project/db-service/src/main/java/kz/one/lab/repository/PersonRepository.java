package kz.one.lab.repository;

import kz.one.lab.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public Person findPersonByMyPhoneNumber(String myPhoneNumber);
}
