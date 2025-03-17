package ru.absolutins.camundaFirstApp.delegate;

import javax.mail.internet.MimeMessage;
import java.util.Base64;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("emailSender")
public class EmailSenderDelegate implements JavaDelegate {


  private final JavaMailSender mailSender;

  public EmailSenderDelegate(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String to = (String) execution.getVariable("email");
    String imageBase64 = (String) execution.getVariable("processedImage");

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setTo(to);
    helper.setFrom("DAIP_TEST@absolutins.ru", "Cat Customizer");
    helper.setSubject("Your Cat Image with Fun Fact!");
    helper.setText("Here's your customized cat image, enjoy!");

    byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
    helper.addAttachment("cat-image.jpg",
        new ByteArrayResource(imageBytes),
        "image/jpeg"
    );

    mailSender.send(message);
  }
}