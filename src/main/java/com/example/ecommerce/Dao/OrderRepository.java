package com.example.ecommerce.Dao;
import com.example.ecommerce.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long>{

    // spring will create a query based on the method name
    // spring will execute this query

    /* select * from orders Left outer join customer
       on orders.customer_id = customer.id
       where customer.email =: email */
    Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email , Pageable pageable);
}
