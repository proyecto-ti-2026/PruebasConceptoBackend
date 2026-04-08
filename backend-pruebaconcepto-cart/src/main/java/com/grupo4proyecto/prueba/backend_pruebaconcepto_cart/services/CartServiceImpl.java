package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.Cart;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.CartItem;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.Product;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.User;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.repositories.CartItemRepository;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.repositories.CartRepository;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.repositories.ProductRepository;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.repositories.UserRepository;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Cart getOrCreateCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    
    @Override
    @Transactional
    public Cart addProductToCart(Long userId, Long productId, Integer quantity) {

    if (quantity == null || quantity <= 0) {
        throw new RuntimeException("La cantidad debe ser mayor a 0");
    }

    Cart cart = getOrCreateCart(userId); //obtiene el carrito del usuario o lo crea en caso que no exista

    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    Optional<CartItem> existingItem = cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst();

    if (existingItem.isPresent()) {
        CartItem item = existingItem.get();
        item.setQuantity(item.getQuantity() + quantity);
    } else {
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        cart.getItems().add(newItem);
    }

    return cartRepository.save(cart);
} 

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).
                 orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario con id:" + userId));
    }

    @Override
    @Transactional
    public Cart removeItemFromCart(Long userId, Long cartItemId) {
        Cart cart = getCartByUserId(userId); //obtiene el carrito del usuario
        
        CartItem cartItem = cartItemRepository.findById(cartItemId). //busca el item por su id
                orElseThrow(() -> new RuntimeException("Item no encontrado"));
                

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("El ítem no pertenece al carrito del usuario");
    }

    cart.getItems().remove(cartItem); //elimina el item de la lista del carrito en memoria
    cartItemRepository.delete(cartItem); //elimina el item de la base de datos

    return cartRepository.save(cart); //guarda el carrito actualizado en la bd
    }

    @Override
    @Transactional
    public Cart updateItemQuantity(Long userId, Long cartItemId, Integer quantity) {
      if (quantity == null || quantity < 0) {
        throw new RuntimeException("La cantidad no puede ser negativa");
    }

    Cart cart = getCartByUserId(userId);

    CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Ítem no encontrado"));

    if (!cartItem.getCart().getId().equals(cart.getId())) {
        throw new RuntimeException("El ítem no pertenece al carrito del usuario");
    }

    if (quantity == 0) {
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
    } else {
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    return cartRepository.save(cart);
}

    @Override
    @Transactional
    public Cart clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        // Obtiene el carrito del usuario

        for (CartItem item : cart.getItems()) {
        cartItemRepository.delete(item);
    }
    // Elimina todos los items de la base de datos

    cart.getItems().clear();
    // Limpia la lista en memoria

    return cartRepository.save(cart);
    // Guarda el carrito vacío
        
    }
}
