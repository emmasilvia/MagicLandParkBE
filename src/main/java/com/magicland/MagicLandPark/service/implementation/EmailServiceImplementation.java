package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImplementation implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImplementation(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNewsletter(String subject, String content, List<String> recipients) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(content);
        message.setTo(recipients.toArray(new String[0]));
        javaMailSender.send(message);
    }

    public void sendSubscriptionConfirmation(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Subscription Confirmation");
        message.setText("Thank you for subscribing to our newsletter!");

        javaMailSender.send(message);
    }

}
