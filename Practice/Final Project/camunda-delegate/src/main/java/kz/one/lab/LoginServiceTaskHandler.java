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

@Component
@ExternalTaskSubscription(topicName = "login")
public class LoginServiceTaskHandler implements ExternalTaskHandler {

    @Autowired
    private PersonServiceClient personServiceClient;

    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {


        String myPhoneNumber = externalTask.getVariable("myPhoneNumber").toString();
        String password = externalTask.getVariable("password").toString();

        Long id = personServiceClient.login(myPhoneNumber, password);

        VariableMap variableMap = Variables.createVariables();
        variableMap.put("person_id", id);

        externalTaskService.complete(externalTask, variableMap);
    }
}
