package com.yablokovs.service;

import com.yablokovs.model.ShoppingCart;
import com.yablokovs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsReceiver {

  @Autowired
  RedisService redisService;

  @SqsListener("elasticache-sqs")
  public void userCacheListener(User user) {
    System.out.println("Received Message for user..." + user.getName());

    redisService.save(user);
    System.out.println("Save Message in Cache");
  }

  @SqsListener("elasticache-sqs")
  public void shoppingCartCacheListener(ShoppingCart shoppingCart) {
    System.out.println("Received Message for user..." + shoppingCart.getProducts());
    System.out.println("Received Message for user..." + shoppingCart.getName());

    System.out.println("Save Message in Cache");
  }
}
