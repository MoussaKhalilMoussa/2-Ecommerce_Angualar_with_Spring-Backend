package com.example.ecommerce.Dao;

import com.example.ecommerce.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// we removed it because we configured cors mapping in the config class
//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
    // spring will execute a query similar to this behind the scenes
    // select * from product where category_id=?
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    // spring will execute this query
    // select * from product p where p.name LIKE CONCAT('%',: name, '%')
    Page<Product> findByNameContaining(@Param("name") String name , Pageable page);







}
