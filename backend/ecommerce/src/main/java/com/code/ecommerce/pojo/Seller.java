package com.code.ecommerce.pojo;

import com.code.ecommerce.pojo.orders.OrderItemDetails;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// merchant or seller details for the items
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_id", nullable = false, unique = true)
    private String sellerId;  // the unique id for the seller

    @Column(name = "seller_name", nullable = false)
    private String sellerName;  // business name of the seller

    @Column(nullable = false, unique = true)
    private String email;  // email of the seller
    private String phone;  // phone number of the seller
    private String address;  // address of the seller

    @Column(name = "active")
    private Boolean active;  // if the seller is active or not

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // date of creation

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;  // date of modification

    // products sold by the seller
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerItemMapping> items = new ArrayList<>();

    // orders associated with the seller
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemDetails> orderItems = new ArrayList<>();


    public Seller(){}
}
