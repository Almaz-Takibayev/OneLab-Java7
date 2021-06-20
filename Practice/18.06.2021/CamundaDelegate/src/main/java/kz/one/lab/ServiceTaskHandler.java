package kz.one.lab;

import kz.one.lab.client.PersonServiceClient;
import kz.one.lab.model.Person;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ExternalTaskSubscription(topicName = "personslist")
public class ServiceTaskHandler implements ExternalTaskHandler {

    @Autowired
    private PersonServiceClient personServiceClient;

    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {


        String name = externalTask.getVariable("name").toString();

        List<Person> result = personServiceClient.findAllByName(name);

        VariableMap variableMap = Variables.createVariables();
        variableMap.put("persons", result);

        externalTaskService.complete(externalTask, variableMap);
    }
}
