package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.model.Subscriber;
import com.magicland.MagicLandPark.repository.SubscriberRepository;
import com.magicland.MagicLandPark.service.EmailService;
import com.magicland.MagicLandPark.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImplementation implements SubscriptionService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private EmailService emailService;

    public SubscriptionServiceImplementation(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public void subscribe(String email) {
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);
        subscriberRepository.save(subscriber);
        emailService.sendSubscriptionConfirmation(subscriber.getEmail());
    }


    public void unsubscribe(String email) {
        Subscriber subscriber = subscriberRepository.findByEmail(email);
        if (subscriber != null) {
            subscriberRepository.delete(subscriber);
        }
    }
}
