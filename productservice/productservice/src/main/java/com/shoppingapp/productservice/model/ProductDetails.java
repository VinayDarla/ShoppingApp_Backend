package com.shoppingapp.productservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product_details")
@Entity
public class ProductDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String productName;
	@Column 
	private String productDescription;
	@Column 
	private float price;
	@Column 
	private String features;
	@Column 
	private int productQuantity;
	@Column 
	private String productStatus;
	
}
