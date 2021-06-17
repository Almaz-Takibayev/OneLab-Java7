package kz.one.lab.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("db-client")
public interface PersonServiceClient {

    @RequestMapping("/persons")
    public String findAllByName(@RequestParam("name") String name);

}
