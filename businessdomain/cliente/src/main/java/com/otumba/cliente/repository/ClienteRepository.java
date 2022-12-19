/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.otumba.cliente.repository;

import com.otumba.cliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author peter
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
