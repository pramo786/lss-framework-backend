package com.lss.framework.service;


import com.lss.framework.dto.response.CaptchaDetails;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@Service
public class CaptchaService {

    public CaptchaDetails generateCaptcha() {
        Random random = new Random();
        int a = random.nextInt(90) + 10;
        int b = random.nextInt(9) + 1;
        String expression = a + " + " + b;
        int result = a + b;

        try {
            BufferedImage image = new BufferedImage(120, 40, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 120, 40);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Arial", Font.BOLD, 24));
            graphics.drawString(expression, 10, 28);
            graphics.dispose();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            String base64Image = Base64.getEncoder().encodeToString(out.toByteArray());

            return new CaptchaDetails(base64Image, String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate captcha image", e);
        }
    }
}