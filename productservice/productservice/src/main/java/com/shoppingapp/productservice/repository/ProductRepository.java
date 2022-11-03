package com.shoppingapp.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingapp.productservice.model.ProductDetails;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, Long>{

	Optional<ProductDetails> findById(long id);
	Optional<ProductDetails> findByProductName(String productname);

//	ProductDetails findByProductName(String productname);
}