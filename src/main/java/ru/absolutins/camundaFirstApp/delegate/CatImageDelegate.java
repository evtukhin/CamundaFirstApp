package ru.absolutins.camundaFirstApp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.absolutins.camundaFirstApp.model.CatImageResponse;

import java.util.Objects;

@Component("catImageFetcher")
public class CatImageDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.thecatapi.com/v1/images/search";

        CatImageResponse[] images = restTemplate.getForObject(url, CatImageResponse[].class);
        assert images != null;
        execution.setVariable("imageUrl", Objects.requireNonNull(images)[0].getUrl());
    }
}
