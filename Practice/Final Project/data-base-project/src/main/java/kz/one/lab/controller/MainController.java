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
public class MainController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/person/{person_id}", method = RequestMethod.GET)
    public Person getPersonInfo(@PathVariable("person_id") Long personId) {
        return personRepository.findById(personId).get();
    }

    @RequestMapping(value = "/person/{person_id}/add_contact", method = RequestMethod.POST)
    public String addContact(@PathVariable("person_id") Long personId, @RequestParam String name, @RequestParam String phoneNumber) {
        Person person = personRepository.findById(personId).get();

        List<Contact> contacts = person.getContacts();

        for (Contact contact : contacts) {
            if (contact.getName().equals(name) || contact.getPhoneNumber().equals(phoneNumber)) {
                return "Incorrect inputs";
            }
        }

        Contact contact = new Contact(name, phoneNumber, person);

        contactRepository.save(contact);

        contacts.add(contact);

        person.setContacts(contacts);

        personRepository.save(person);

        return "You added contact successfully";
    }

    @RequestMapping(value = "/person/{person_id}/delete_contact", method = RequestMethod.DELETE)
    public String deleteContact(@PathVariable("person_id") Long personId, @RequestParam String name) {
        Person person = personRepository.findById(personId).get();

        List<Contact> contacts = new ArrayList<Contact>();

        for (Contact contact : person.getContacts()) {
            if (!contact.getName().equals(name)) {
                contacts.add(contact);
            }
            else {
                contactRepository.delete(contact);
            }
        }

        person.setContacts(contacts);

        personRepository.save(person);

        return "You deleted contact successfully";
    }

    @RequestMapping(value = "/person/{person_id}/update_contact", method = RequestMethod.PUT)
    public String updateContact(@PathVariable("person_id") Long personId, @RequestParam Long contactId,@RequestParam String name, @RequestParam String phoneNumber) {
        Person person = personRepository.findById(personId).get();

        List<Contact> contacts = new ArrayList<Contact>();

        for (Contact contact : person.getContacts()) {
            if (contact.getId() != contactId) {
                contacts.add(contact);
            }
            else {
                Contact updatedContact = contactRepository.save(new Contact(contact.getId(), name, phoneNumber, person));
                contacts.add(updatedContact);
            }
        }

        person.setContacts(contacts);

        personRepository.save(person);

        return "You updated contact successfully";
    }

    @RequestMapping(value = "/person/{person_id}/contacts", method = RequestMethod.GET)
    public List<Contact> personsContacts(@PathVariable("person_id") Long personId) {
        return personRepository.findById(personId).get().getContacts();
    }
}
