package com.magicland.MagicLandPark.service;

import java.util.List;

public interface EmailService {

    void sendNewsletter(String subject, String content, List<String> recipients);

    void sendSubscriptionConfirmation(String recipientEmail);
}
