package ru.absolutins.camundaFirstApp.delegate;

import java.util.regex.Pattern;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("emailValidator")
public class EmailValidationDelegate implements JavaDelegate {

  private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

  public EmailValidationDelegate() {
    System.out.println("Bean EmailValidationDelegate class was created!");
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String email = (String) execution.getVariable("email");

    if (!EMAIL_REGEX.matcher(email).matches()) {
      throw new BpmnError("INVALID_EMAIL");
    }
  }
}