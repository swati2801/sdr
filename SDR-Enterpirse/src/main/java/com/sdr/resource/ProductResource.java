package com.sdr.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdr.bean.*;
import com.sdr.exception.ResourceNotFoundException;
import com.sdr.service.ProductService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "SDR Enterprises", version = "1.0.0", description = "The stock management software"))
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:5000", "http://localhost:8000", "https://sdrgoldindia.netlify.app/" })
@RestController
@RequestMapping("/SDR")
@Tag(name = "Product create, update, delete rest endpoints")
public class ProductResource {

	@Autowired
	private ProductService productService;
	//private static final Logger log = LoggerFactory.getLogger(ProductResource.class);
	

	@GetMapping(path = "hello")
	public String hello() {		
		return "Hello Swati..!! Yooo!..!! This is test method..!!";
	}

	@Operation(summary = "List All products", description = "This API gets all the products availible in DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")) })
	@GetMapping(path = "allProducts")
	public List<Product> listProducts() {
		return productService.getAllProducts();
	}


	@Operation(summary = "Add product", description = "This API adds products into the DB", responses = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json")) })
	@PostMapping("/addProduct/{name}/{quantity}/{type}/{finalProduct}")
	public ResponseEntity<Object> createProduct(
			@NotNull @Parameter(description = "Enter name of product", required = true) @Valid @PathVariable String name,
			@NotNull @Parameter(description = "Enter in house quantity", required = true) @Valid @PathVariable Integer quantity,
			@NotNull @Parameter(description = "Enter name of product", required = true) @Valid @PathVariable Integer type,
			@NotNull @Parameter(description = "Enter in house quantity", required = true) @Valid @PathVariable Integer finalProduct	) {
		Product newProduct = productService.addProduct(new Product(name, quantity, type, finalProduct));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Add product", description = "This API adds products into the DB", responses = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")) })
	@PostMapping("/addProductTest")
	public ResponseEntity<Object> createProductTest(
			@RequestBody ProductDTO product) {
		Product newProduct = productService.addProduct(new Product(product.getName(), product.getQuantity(), product.getType(), product.getFinalProduct()));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//
	@Operation(summary = "Update product quantity", description = "This API is for Manufactured products, it decreases arw material and increase in final product quantity", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")) })
	@PutMapping("/updateProductQuantity")
	public boolean updateProductQuantity(@RequestBody UpdateProductDTO product) {
		return productService.updateProductQuantity(product);
	}
	
	@Operation(summary = "Update product", description = "This API updates quantity of a product", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")) })
	@PutMapping("/updateProduct")
	public ResponseEntity<Object> updateProduct(
			@RequestBody ProductDTO product) throws ResourceNotFoundException{
		Product newProduct = productService.updateProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
		return ResponseEntity.ok(location);
	}
	
	
	@Operation(summary = "Get Product By Id", description = "This API get Product details By ID from the DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Product Not found", content = @Content(mediaType = "application/json")) })
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductDTO> getProductById(
			@NotNull @Parameter(description = "Enter product ID", required = true) @Valid @PathVariable Integer id) 
					throws ResourceNotFoundException{

		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
		
	}
	
	
	@Operation(summary = "Get Raw MAterial list By Final Product ID",
			description = "This API get Raw MAterial list By Final Product ID from the DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json")) })
	@GetMapping("/getRawMaterialByFinalProduct/{finalProduct}")
	public List<Product> getRawMaterialByFinalProduct(
			@NotNull @Parameter(description = "Enter ID of product", required = true) 
			@Valid @PathVariable Integer finalProduct)  
					throws ResourceNotFoundException{
		return productService.getRawMaterialByFinalProduct(finalProduct);
	}
	
	
	@DeleteMapping("/deleteProduct/{id}")
	@Operation(summary = "Delete product by ID", description = "This API delete the product DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json")) })
	public void deleteProduct(
			@NotNull @Parameter(description = "Enter product ID", required = true) @Valid @PathVariable Integer id) throws ResourceNotFoundException{

		productService.deleteProduct(id);
	}


	@Operation(summary = "Get Product By name", description = "This API get Product details By name from the DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Product Not found", content = @Content(mediaType = "application/json")) })
	@GetMapping("/getProductName/{name}")
	public boolean getProductByName(
			@NotNull @Parameter(description = "Enter name of product", required = true) @Valid @RequestParam(value = "Product Name", required = true) String productName) 
	throws ResourceNotFoundException{
		
		return productService.getProductByName(productName);
	}

	@Operation(summary = "Get Product By name alike substring",
			description = "This API get Product details By name containing input(like) from the DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json")) })
	@GetMapping("/getAllProductName/{name}")
	public List<Product> getAllProductByName(
			@NotNull @Parameter(description = "Enter name of product", required = true) 
			@Valid @RequestParam(value = "Product Name", required = true) String productName)  
					throws ResourceNotFoundException{
		return productService.getAllProductByName(productName);
	}
	
}
