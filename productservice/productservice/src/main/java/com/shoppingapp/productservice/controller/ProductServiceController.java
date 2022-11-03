package com.shoppingapp.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.productservice.exception.InvalidTokenException;
import com.shoppingapp.productservice.exception.ProductNotFoundException;
import com.shoppingapp.productservice.feignclients.AuthFeign;
import com.shoppingapp.productservice.model.ProductDetails;
import com.shoppingapp.productservice.service.ProductServices;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1.0/Shopping")
@Slf4j
public class ProductServiceController {
	
	@Autowired
	AuthFeign authfeign;
	@Autowired
	ProductServices productservice;
	@PostMapping("/{productname}/add")
	public ResponseEntity<Object> addProduct(@RequestHeader("Authorization") String token,
			@PathVariable String productname,@RequestBody ProductDetails productDetails) throws InvalidTokenException{
		log.info("inside product service controller to add products");
		if(authfeign.getAdminValidity(token).getBody().getValid()) {
			return productservice.addProduct(productname, productDetails);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");	
	}
	@GetMapping("/all")
	public ResponseEntity<Object> getAllTweets(@RequestHeader("Authorization") String token) throws InvalidTokenException{
		log.info("inside product service controller to get all products");
		if(authfeign.getValidity(token).getBody().getValid()) {
		return productservice.getAllProducts();
		}
		log.info("inside tweet service controller ,token expired login again");
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	
	@GetMapping("/adminall")
	public ResponseEntity<Object> getAllProds(@RequestHeader("Authorization") String token) throws InvalidTokenException{
		log.info("inside product service controller to get all products");
		if(authfeign.getAdminValidity(token).getBody().getValid()) {
		return productservice.getAllProducts();
		}
		log.info("inside tweet service controller ,token expired login again");
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	
	@GetMapping("/{productname}")
	public ResponseEntity<Object> getProductsByProductname(@RequestHeader("Authorization") String token,
			@PathVariable String productname) throws InvalidTokenException{
		log.info("inside product service controller to get product by productname");
		if(authfeign.getValidity(token).getBody().getValid()) {
		return productservice.getProductsByProductName(productname);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@PutMapping("/{productname}/update/{id}")
	public ResponseEntity<Object> updateProduct(@RequestHeader("Authorization") String token,
			@PathVariable("productname") String productname,@PathVariable("id") long id,
			@RequestBody ProductDetails productdetails) throws ProductNotFoundException, InvalidTokenException{
		log.info("inside product service controller to Update products");
		if(authfeign.getAdminValidity(token).getBody().getValid()) {
		return productservice.updateProduct(id, productdetails);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@DeleteMapping("/{productname}/delete/{id}")
	public ResponseEntity<Object> deleteTweet(@RequestHeader("Authorization") String token,
			@PathVariable("productname") String productname,@PathVariable("id") long id)
					throws ProductNotFoundException, InvalidTokenException{
		log.info("deleted");
		log.info("inside product service controller to delete products");
//		Properties props = new Properties();
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, longSerializer.class.getName());
		if(authfeign.getAdminValidity(token).getBody().getValid()) {
//			kafkaTemplate.send(AppConfigs.topicName,"delete", id);
			
			return productservice.deleteProduct(id);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
		
	}

}