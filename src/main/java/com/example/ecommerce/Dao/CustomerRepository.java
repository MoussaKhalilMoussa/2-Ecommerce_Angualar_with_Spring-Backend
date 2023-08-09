package com.example.ecommerce.Dao;

import com.example.ecommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(collectionResourceRel = "customers" , path = "customers")
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByEmail(String theEmail);
}
