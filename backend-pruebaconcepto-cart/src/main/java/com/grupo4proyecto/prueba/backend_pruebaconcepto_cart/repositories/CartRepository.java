package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.Cart;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.User;

public interface CartRepository extends CrudRepository<Cart, Long>{
    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUserId(Long userId);
}
