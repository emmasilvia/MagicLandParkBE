package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.persoana.PersoanaDTO;
import com.magicland.MagicLandPark.controller.dto.persoana.SubscriberDTO;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.model.Subscriber;
import com.magicland.MagicLandPark.model.Tip;
import com.magicland.MagicLandPark.service.PersoanaService;
import com.magicland.MagicLandPark.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class NewsletterController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    public NewsletterController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeUser(@RequestBody String email) {
        subscriptionService.subscribe(email);
        return ResponseEntity.ok("Subscribed successfully");
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribeUser(@RequestBody String email) {
        subscriptionService.unsubscribe(email);
        return ResponseEntity.ok("Unsubscribed successfully");
    }

    public Subscriber mapSubscriberDtoToSubscriber(SubscriberDTO subscriberDTO){

        Subscriber s1 = new Subscriber();
        s1.setEmail(subscriberDTO.getEmail());
        return s1;
    }

    public SubscriberDTO mapSubscriberToSubscriberDto(Subscriber subscriber){
        SubscriberDTO sd1 = new SubscriberDTO();
        sd1.setEmail(subscriber.getEmail());

        return sd1;
    }
}


