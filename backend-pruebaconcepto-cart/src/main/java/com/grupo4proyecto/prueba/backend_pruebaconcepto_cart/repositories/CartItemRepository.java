package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.repositories;

import org.springframework.data.repository.CrudRepository;

import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
