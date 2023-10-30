package com.backend.products.models;



import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="products")
@Getter
@Setter
public class ProductModel implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String brand;
    private String model;
    private int year;
    private double kilometers;
    private double price;
    private String description;
    private String imageName;

}
