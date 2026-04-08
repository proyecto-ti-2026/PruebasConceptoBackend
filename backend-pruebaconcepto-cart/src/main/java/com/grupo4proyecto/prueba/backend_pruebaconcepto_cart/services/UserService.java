package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.services;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.User;
import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    
    User save(User user);

    void remove(Long id);

    
}
