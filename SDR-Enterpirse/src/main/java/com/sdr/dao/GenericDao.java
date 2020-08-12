package com.sdr.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.sdr.bean.Product;


//@Repository
@Component
public interface GenericDao extends JpaRepository<Product, Integer>{
	
	@Query("FROM Product where name = ?1")
    public Optional<Product> findByName(String name);
	
	
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	List<Product> findByNameContainingIgnoreCase(String name);
	
	List<Product> findByFinalProduct(Integer finalProduct);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.id = :id")
    int updateProductDTO(@Param("quantity") Integer quantity, @Param("id") Integer id);
	//void UpdateProductDTO(UpdateProductDTO dto);
	
	//
	
}
