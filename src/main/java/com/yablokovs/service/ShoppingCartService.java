package com.yablokovs.service;

import com.yablokovs.model.Product;
import com.yablokovs.model.ShoppingCart;
import com.yablokovs.repository.ShoppingCartRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ProductService productService;
    private SqsSender sqsSender;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService, SqsSender sqsSender) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.sqsSender = sqsSender;
    }

    private int getRandomNumber() {
        return ((int) (Math.random() * 5) + 1);
    }

    public ShoppingCart getShoppingCartByName(String name) {
        Optional<ShoppingCart> byId = shoppingCartRepository.findByName(name);
        return byId.orElseGet(() -> createShoppingCart(name));
    }

    private ShoppingCart createShoppingCart(String name) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProducts(new ArrayList<>());
        shoppingCart.setName(name);
        ShoppingCart save = shoppingCartRepository.save(shoppingCart);
        return save;
    }

    public ShoppingCart putDefaultProductToCart(String name) {
        Product product = generateProduct();
        product.setId(1L);
        productService.putProduct(product);
        ShoppingCart shoppingCart = putProductToCart(product, name);
        return shoppingCart;
    }

    private Product generateProduct() {
        Product product = new Product();
        product.setName("default");
        product.setQuantity(getRandomNumber());
        product.setPrice(getRandomNumber());
        product.calculateSum();
        return product;
    }

    public ShoppingCart putProductToCart(Product product, String name) {
        ShoppingCart shoppingCartById = getShoppingCartByName(name);
        shoppingCartById.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCartById);
    }

    public ShoppingCart test(String name) {
        ShoppingCart shoppingCart = getShoppingCartByName(name);

        List<Product> products = shoppingCart.getProducts();
        if (!products.isEmpty()) {
            Product product = products.get(0);
            Long id = product.getId();
            Product productById = productService.findProductById(id);
            System.out.println();
        }


        shoppingCart.getProducts().add(generateProduct());
        ShoppingCart save = shoppingCartRepository.save(shoppingCart);
        return save;
    }

    public ShoppingCart send(String cartName) {
        Optional<ShoppingCart> byName = shoppingCartRepository.findByName(cartName);

        ShoppingCart shoppingCart = byName.orElseGet(() -> createShoppingCart(cartName));
        sqsSender.sendShoppingCart(shoppingCart);

        return shoppingCart;
    }
}
