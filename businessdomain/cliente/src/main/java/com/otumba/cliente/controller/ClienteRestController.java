/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otumba.cliente.controller;

import com.otumba.cliente.entities.Cliente;
import com.otumba.cliente.services.IClienteService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peter
 */

// va a permitir a la aplicacion angular conectarse al servicio
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    @GetMapping("/test")
    public String get(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
        return "OK";
    }

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
           return clienteService.findAll();
    }
    
    @GetMapping("clientes/{id}")
    public Cliente show (@PathVariable Long id){
        return clienteService.findById(id);
    }
    
    @PostMapping("/clientes")
    // por default contesta 200 pero en este caso queremos responder un codigo 201
    @ResponseStatus (HttpStatus.CREATED)
    public Cliente create (@RequestBody Cliente cliente){
        // lo siguiente se puede poner para asignar fecha pero mejor se va a asignar al prepersistir dentro de el entity
        // en la clase cliente
       // cliente.setCreateAt(new Date());
       
        return clienteService.save(cliente);
    }
    
}
