package com.example.ecommerce.Dao;

import com.example.ecommerce.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// we removed it because we configured cors mapping in the config class
// after removing it and configuring another code I m getting error so  did not remove it
//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries" , path = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
