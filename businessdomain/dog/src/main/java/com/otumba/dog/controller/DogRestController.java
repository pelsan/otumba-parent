/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otumba.dog.controller;

import com.otumba.dog.entities.Dog;
import com.otumba.dog.repository.DogRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peter
 */
@RestController
@RequestMapping("/dog")
public class DogRestController {

    @Autowired
    DogRepository dogRepository;

    @GetMapping()
    public List<Dog> list() {
        return dogRepository.findAll();
    }

    @GetMapping("/{id}")
    public Dog get(@PathVariable long id, @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
        return dogRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody Dog input) {
        Dog find = dogRepository.findById(id).get();
        if (find != null) {
            find.setAge(input.getAge());
            find.setName(input.getName());
        }
        Dog save = dogRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Dog input, @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println(String.format("post: Header '%s' = %s", key, value));
        });

        Dog save = dogRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Dog> findById = dogRepository.findById(id);
        if (findById.get() != null) {
            dogRepository.delete(findById.get());
        }
        return ResponseEntity.ok().build();
    }

}
