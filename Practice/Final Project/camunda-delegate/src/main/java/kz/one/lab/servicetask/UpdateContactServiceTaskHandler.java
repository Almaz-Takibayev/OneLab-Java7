package kz.one.lab.servicetask;

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
@ExternalTaskSubscription(topicName = "update_contact")
public class UpdateContactServiceTaskHandler implements ExternalTaskHandler {

    @Autowired
    private PersonServiceClient personServiceClient;

    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        Long personId = externalTask.getVariable("personId");
        Long contactId = externalTask.getVariable("contactId");
        String name = externalTask.getVariable("name");
        String phoneNumber = externalTask.getVariable("phoneNumber");

        Contact contact = personServiceClient.updateContact(personId, contactId, name, phoneNumber);

        VariableMap variableMap = Variables.createVariables();
        variableMap.put("updated_contact", contact);

        externalTaskService.complete(externalTask, variableMap);
    }
}
