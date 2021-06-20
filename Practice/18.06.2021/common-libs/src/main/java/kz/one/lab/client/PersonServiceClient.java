package kz.one.lab.client;

import kz.one.lab.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("db-client")
public interface PersonServiceClient {

    @RequestMapping("/persons")
    public List<Person> findAllByName(@RequestParam("name") String name);

}
