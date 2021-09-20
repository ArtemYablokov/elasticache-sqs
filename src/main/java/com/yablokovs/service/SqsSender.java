package com.yablokovs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.yablokovs.model.ShoppingCart;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class SqsSender {

    private QueueMessagingTemplate queueMessagingTemplate;


    public SqsSender(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void sendShoppingCart(ShoppingCart shoppingCart) {
        System.out.println("Sending shoppingCart to SQS..." + shoppingCart.getName());
        System.out.println("Sending shoppingCart to SQS..." + shoppingCart.getProducts());
        queueMessagingTemplate.convertAndSend("sqs-queue", shoppingCart);
    }
}
