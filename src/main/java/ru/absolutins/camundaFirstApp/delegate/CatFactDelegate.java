package ru.absolutins.camundaFirstApp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.absolutins.camundaFirstApp.model.CatFactResponse;

import java.util.Objects;

@Component("catFactFetcher")
public class CatFactDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://catfact.ninja/fact";

        CatFactResponse response = restTemplate.getForObject(url, CatFactResponse.class);
        execution.setVariable("catFact", Objects.requireNonNull(response).getFact());
    }
}