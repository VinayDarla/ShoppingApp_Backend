package com.shoppingapp.productservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingapp.productservice.exception.ProductNotFoundException;
import com.shoppingapp.productservice.model.ProductDetails;

@Service
public interface ProductServices {

	public ResponseEntity<Object> addProduct(String productname,ProductDetails productDetails);
	public ResponseEntity<Object> getAllProducts();
	public ResponseEntity<Object> getProductsByProductName(String productname);
	public ResponseEntity<Object> updateProduct(long id,ProductDetails productDetails) throws ProductNotFoundException;
	public ResponseEntity<Object> deleteProduct(long id) throws ProductNotFoundException;
}
