/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.otumba.dog.repository;

import com.otumba.dog.entities.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author peter
 */
public interface DogRepository extends JpaRepository<Dog, Long> {    

}
