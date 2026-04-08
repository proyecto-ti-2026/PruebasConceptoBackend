package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.Cart;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService service;

    public CartController(CartService service){
        this.service = service;
    }

    @GetMapping("{userId}")
    public Cart getOrCreateCart(@PathVariable Long userId){
        return service.getOrCreateCart(userId);
    }

    
    @PostMapping("{userId}/items")
    public Cart addProductToCart(
        @PathVariable Long userId,
        @RequestParam Long productId,
        @RequestParam Integer quantity) {
    return service.addProductToCart(userId, productId, quantity);
    
}
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(service.getCartByUserId(userId));
}

    @PutMapping("/{userId}/items/{cartItemId}")
    public Cart updateItemQuantity(
        @PathVariable Long userId,
        @PathVariable Long cartItemId,
        @RequestParam Integer quantity) {
    return service.updateItemQuantity(userId, cartItemId, quantity);
}

    @DeleteMapping("/{userId}/items/{cartItemId}")
    public Cart removeItemFromCart(
        @PathVariable Long userId,
        @PathVariable Long cartItemId) {
    return service.removeItemFromCart(userId, cartItemId);
}

    @DeleteMapping("/{userId}/items")
    public Cart clearCart(@PathVariable Long userId) {
    return service.clearCart(userId);
}



}
