package ru.absolutins.camundaFirstApp.delegate;

import java.util.Objects;
import lombok.Data;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("catImageFetcher")
public class CatImageDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.thecatapi.com/v1/images/search";

    CatImage[] images = restTemplate.getForObject(url, CatImage[].class);
    assert images != null;
    execution.setVariable("imageUrl", Objects.requireNonNull(images)[0].getUrl());
  }

  @Data
  private static class CatImage {

    private String id;
    private String url;
    private int width;
    private int height;
  }
}
