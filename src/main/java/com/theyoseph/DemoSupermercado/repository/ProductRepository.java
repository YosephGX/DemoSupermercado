package com.theyoseph.DemoSupermercado.repository;

import com.theyoseph.DemoSupermercado.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Buscar producto por nombre
    Optional<Product> findByName(String nombre);
}
