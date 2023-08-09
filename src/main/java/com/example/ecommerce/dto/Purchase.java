package com.example.ecommerce.dto;

import com.example.ecommerce.Entity.Address;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Order;
import com.example.ecommerce.Entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
