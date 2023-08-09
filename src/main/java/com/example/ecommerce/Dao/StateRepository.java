package com.example.ecommerce.Dao;

import com.example.ecommerce.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// we removed it because we configured cors mapping in the config class
// after removing it and configuring another code I m getting error so  did not remove it
//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State , Integer> {
    List<State> findByCountryCode(@Param("code") String code);
}
