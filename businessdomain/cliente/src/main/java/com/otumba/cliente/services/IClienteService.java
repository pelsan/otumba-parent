/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.otumba.cliente.services;

import com.otumba.cliente.entities.Cliente;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author peter
 */
public interface IClienteService {
    
    public List<Cliente> findAll();
    
    public Page<Cliente> findAll(Pageable pageable);
    
    public Cliente save(Cliente cliente);
    
    public void delete (Long id);
    
    public Cliente findById(Long id);
    
}
