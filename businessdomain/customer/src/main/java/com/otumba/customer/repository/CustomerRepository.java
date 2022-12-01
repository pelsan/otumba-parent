/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.otumba.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.otumba.customer.entities.Customer;

/**
 *
 * @author pelsa
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
