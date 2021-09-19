package com.yablokovs.service;

import com.yablokovs.model.ShoppingCart;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsReceiver {


    @SqsListener("sqs-queue")
    public void shoppingCartCacheListener(ShoppingCart shoppingCart) {
        System.out.println("Received Message for user..." + shoppingCart.getProducts());
        System.out.println("Received Message for user..." + shoppingCart.getName());

        System.out.println("Save Message in Cache");
    }
}
