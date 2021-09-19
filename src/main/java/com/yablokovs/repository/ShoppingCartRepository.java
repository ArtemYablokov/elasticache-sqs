package com.yablokovs.repository;

import com.yablokovs.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {


    Optional<ShoppingCart> findByName(String name);
}
