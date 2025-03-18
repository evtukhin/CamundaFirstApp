package ru.absolutins.camundaFirstApp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Base64;

@Component("imageProcessor")
public class ImageProcessingDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String imageUrl = (String) execution.getVariable("imageUrl");
        String fact = (String) execution.getVariable("catFact");
        String email = (String) execution.getVariable("email");

        // 1. Download image
        //TODO replace deprecated URL
        BufferedImage image = ImageIO.read(new URL(imageUrl));
//    BufferedImage image = ImageIO.read(new URI.toURL(imageUrl));

        // 2. Add text
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        graphics.setColor(Color.WHITE);
        graphics.drawString(fact, 50, 50);
        graphics.drawString("Sent to: " + email, 50, image.getHeight() - 50);
        graphics.dispose();

        // 3. Save processed image
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        execution.setVariable("processedImage", Base64.getEncoder()
                .encodeToString(baos.toByteArray()));
    }
}
