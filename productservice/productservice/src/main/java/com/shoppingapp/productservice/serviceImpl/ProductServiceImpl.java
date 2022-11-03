package com.shoppingapp.productservice.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingapp.productservice.exception.ProductNotFoundException;
import com.shoppingapp.productservice.model.ProductDetails;
import com.shoppingapp.productservice.repository.ProductRepository;
import com.shoppingapp.productservice.service.ProductServices;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductServices {
	@Autowired
	ProductRepository productrepo;
	@Override
	public ResponseEntity<Object> addProduct(String productname,ProductDetails productDetails) {
		log.info("inside product service Implementation to add product");
			if(productrepo.findByProductName(productname).isPresent()) {
				Optional<ProductDetails> product = productrepo.findByProductName(productname);
				product.get().setProductQuantity(product.get().getProductQuantity() + productDetails.getProductQuantity());
				product.get().setProductStatus("HURRY UP TO PURCHASE");
				productrepo.save(product.get());
			}
			else {
				if(productDetails.getProductQuantity()<=0) {
					productDetails.setProductStatus("OUT OF STOCK");
				}
				else {
			productDetails.setProductStatus("HURRY UP TO PURCHASE");
			
				}
				productrepo.save(productDetails);
			}
			
		
			return new ResponseEntity<Object>("Product Added Successfully",HttpStatus.CREATED);
	}
	@Override
	public ResponseEntity<Object> getAllProducts() {
			log.info("inside product service Implementation to get all products");
			List<ProductDetails> products=productrepo.findAll();
			if(!products.isEmpty() && products.size()>0)
				return new ResponseEntity<Object>(products,HttpStatus.OK);
			return new ResponseEntity<Object>("No Products Found",HttpStatus.NO_CONTENT);
		}
	@Override
	public ResponseEntity<Object> getProductsByProductName(String productname) {
		log.info("inside product service Implementation to get products by product");
		List<ProductDetails> product= productrepo.findAll().stream().filter(o->o.getProductName().equals(productname))
				.collect(Collectors.toList());
//		Optional<ProductDetails> product= productrepo.findByProductname(productname);
		if(!product.isEmpty() && product.size()>0)
			return new ResponseEntity<Object>(product,HttpStatus.OK);
		return new ResponseEntity<Object>("No Products Found",HttpStatus.NO_CONTENT);
	}
	@Override
	public ResponseEntity<Object> updateProduct(long id, ProductDetails productDetails)
			throws ProductNotFoundException {
		log.info("inside product service Implementation to update product data");
		Optional<ProductDetails> product =productrepo.findById(id);
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found exception");
		}
		product.get().setProductName(productDetails.getProductName());
		product.get().setPrice(productDetails.getPrice());
		product.get().setProductDescription(productDetails.getProductDescription());
		product.get().setProductQuantity(productDetails.getProductQuantity());
		if(productDetails.getProductQuantity()<=0) {
			product.get().setProductStatus("OUT OF STOCK");
		}
		else {
			product.get().setProductStatus("HURRY UP TO PURCHASE");
		}
		productrepo.save(product.get());
		return new ResponseEntity<Object>("Updated Product Successfully",HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Object> deleteProduct(long id) throws ProductNotFoundException {
		log.info("inside product service Implementation to delete product");
		Optional<ProductDetails> product=productrepo.findById(id);
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found exception");
		}
		productrepo.deleteById(id);
		return new ResponseEntity<Object>("Deleting Product Successfully",HttpStatus.OK);
	}
	
}

