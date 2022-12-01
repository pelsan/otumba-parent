
package com.otumba.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.otumba.customer.entities.Customer;

/**
 *
 * @author pelsa
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
