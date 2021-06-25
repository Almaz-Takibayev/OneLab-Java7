package kz.one.lab;

import kz.one.lab.client.PersonServiceClient;
import kz.one.lab.model.Contact;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription(topicName = "delete_contact")
public class DeleteContactServiceTaskHandler implements ExternalTaskHandler {

    @Autowired
    private PersonServiceClient personServiceClient;

    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        Long personId = externalTask.getVariable("personId");
        Long contactId = externalTask.getVariable("contactId");

        Contact contact = personServiceClient.deleteContact(personId, contactId);

        VariableMap variableMap = Variables.createVariables();
        variableMap.put("deleted_contact", contact);

        externalTaskService.complete(externalTask, variableMap);
    }
}
