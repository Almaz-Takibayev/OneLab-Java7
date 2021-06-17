package kz.one.lab;

import com.netflix.discovery.EurekaClient;
import kz.one.lab.client.PersonServiceClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DelegateService implements JavaDelegate {
//    @Qualifier("eurekaClient")
//    @Autowired
//    private EurekaClient eurekaClient;

    @Autowired
    private PersonServiceClient personServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String name = delegateExecution.getVariable("name").toString();

        String result = personServiceClient.findAllByName(name);

        delegateExecution.setVariable("result", result);
    }
}
