package kz.one.lab.client;

import kz.one.lab.model.Contact;
import kz.one.lab.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("db-client")
@RequestMapping(value = "/person")
public interface PersonServiceClient {

    @RequestMapping(value = "/{person_id}", method = RequestMethod.GET)
    public Person getPersonInfo(@PathVariable("person_id") Long personId);

    @RequestMapping(value = "/{person_id}/add_contact", method = RequestMethod.POST)
    public Contact addContact(@PathVariable("person_id") Long personId, @RequestParam("name") String name, @RequestParam("phoneNumber") String phoneNumber);

    @RequestMapping(value = "/{person_id}/delete_contact", method = RequestMethod.DELETE)
    public Contact deleteContact(@PathVariable("person_id") Long personId, @RequestParam("contactId") Long contactId);

    @RequestMapping(value = "/{person_id}/update_contact", method = RequestMethod.PUT)
    public Contact updateContact(@PathVariable("person_id") Long personId, @RequestParam("contactId") Long contactId,@RequestParam("name") String name, @RequestParam("phoneNumber") String phoneNumber);

    @RequestMapping(value = "/{person_id}/contacts", method = RequestMethod.GET)
    public List<Contact> personsContacts(@PathVariable("person_id") Long personId);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Long login(@RequestParam("myPhoneNumber") String myPhoneNumber, @RequestParam("password") String password);
}
