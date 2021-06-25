package kz.one.lab.controller;

import kz.one.lab.entity.Contact;
import kz.one.lab.entity.Person;
import kz.one.lab.repository.ContactRepository;
import kz.one.lab.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class MainController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/{person_id}", method = RequestMethod.GET)
    public Person getPersonInfo(@PathVariable("person_id") Long personId) {
        return personRepository.findById(personId).get();
    }

    @RequestMapping(value = "/{person_id}/add_contact", method = RequestMethod.POST)
    public Contact addContact(@PathVariable("person_id") Long personId, @RequestParam String name, @RequestParam String phoneNumber) {
        Person person = personRepository.findById(personId).get();

        List<Contact> contacts = person.getContacts();

        for (Contact contact : contacts) {
            if (contact.getName().equals(name) || contact.getPhoneNumber().equals(phoneNumber)) {
                return null;
            }
        }

        Contact addedContact = contactRepository.save(new Contact(name, phoneNumber, person));

        return addedContact;
    }

    @RequestMapping(value = "/{person_id}/delete_contact", method = RequestMethod.DELETE)
    public Contact deleteContact(@PathVariable("person_id") Long personId, @RequestParam Long contactId) {
        Person person = personRepository.findById(personId).get();

        for (Contact contact : person.getContacts()) {
            if (contact.getId() == contactId) {
                contactRepository.delete(contact);
                return contact;
            }
        }

        return null;
    }

    @RequestMapping(value = "/{person_id}/update_contact", method = RequestMethod.PUT)
    public Contact updateContact(@PathVariable("person_id") Long personId, @RequestParam Long contactId,@RequestParam String name, @RequestParam String phoneNumber) {
        Person person = personRepository.findById(personId).get();

        for (Contact contact : person.getContacts()) {
            if (contact.getId() == contactId) {
                Contact updatedContact = contactRepository.save(new Contact(contact.getId(), name, phoneNumber, person));
                return updatedContact;
            }
        }

        return null;
    }

    @RequestMapping(value = "/{person_id}/contacts", method = RequestMethod.GET)
    public List<Contact> personsContacts(@PathVariable("person_id") Long personId) {
        return personRepository.findById(personId).get().getContacts();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Long login(@RequestParam String myPhoneNumber, @RequestParam String password) {
        Person person = personRepository.findPersonByMyPhoneNumber(myPhoneNumber);

        if (person.getPassword().equals(password)) {
            return person.getId();
        }

        return -1L;
    }
}
