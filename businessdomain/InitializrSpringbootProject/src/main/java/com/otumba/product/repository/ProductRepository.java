/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.otumba.product.repository;

import com.otumba.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pelsa
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
