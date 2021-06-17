package com.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int number1 = (int)delegateExecution.getVariable("number1");
        int number2 = (int) (Math.random());

        int result = number1 + number2;

        delegateExecution.setVariable("result", result);
    }
}
