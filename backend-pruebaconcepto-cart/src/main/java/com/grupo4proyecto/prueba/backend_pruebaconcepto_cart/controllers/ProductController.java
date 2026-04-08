package com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.controllers;

import java.util.List;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.models.entities.Product;
import com.grupo4proyecto.prueba.backend_pruebaconcepto_cart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> list(){
        return service.findAll();

    }

}
