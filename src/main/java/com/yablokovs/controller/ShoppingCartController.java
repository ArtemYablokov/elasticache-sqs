package com.yablokovs.controller;

import com.yablokovs.model.ShoppingCart;
import com.yablokovs.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart/")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("{cartName}")
//    @Transactional()
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable String cartName) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartByName(cartName));
    }

    @PutMapping("{cartName}")
//    @Transactional()
    public ResponseEntity<ShoppingCart> putDefaultProductToCart(@PathVariable String cartName) {
        return ResponseEntity.ok(shoppingCartService.putDefaultProductToCart(cartName));
    }

    @GetMapping("test/{cartName}")
//    @Transactional()
    public ResponseEntity<ShoppingCart> test(@PathVariable String cartName) {
        return ResponseEntity.ok(shoppingCartService.test(cartName));
    }

    @GetMapping("send/{cartName}")
//    @Transactional()
    public ResponseEntity<ShoppingCart> send(@PathVariable String cartName) {
        return ResponseEntity.ok(shoppingCartService.send(cartName));
    }

}
