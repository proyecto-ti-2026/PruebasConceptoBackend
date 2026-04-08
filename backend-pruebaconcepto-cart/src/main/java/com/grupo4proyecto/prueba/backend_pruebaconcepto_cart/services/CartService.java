package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.services;

import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.Cart;

public interface CartService {

    Cart getOrCreateCart(Long userId); // Obtiene el carrito del usuario o lo crea si no existe

    Cart addProductToCart(Long userId, Long productId, Integer quantity); // Agrega un producto al carrito del usuario

    Cart getCartByUserId(Long userId); // Obtiene el carrito asociado a un usuario

    Cart removeItemFromCart(Long userId, Long cartItemId); // Elimina un ítem específico del carrito

    Cart updateItemQuantity(Long userId, Long cartItemId, Integer quantity); // Actualiza la cantidad de un ítem del carrito

    Cart clearCart(Long userId); // Elimina todos los ítems del carrito

}
