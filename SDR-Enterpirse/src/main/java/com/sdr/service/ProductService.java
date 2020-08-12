package com.sdr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdr.bean.Product;
import com.sdr.bean.ProductDTO;
import com.sdr.bean.UpdateProductDTO;
import com.sdr.bean.UpdateProductListDTO;
import com.sdr.dao.GenericDao;
import com.sdr.exception.ResourceNotFoundException;
import com.sdr.util.ProductUtil;

@Service
public class ProductService {

	@Autowired
	private GenericDao dao;

	public List<Product> getAllProducts() {
		return dao.findAll();
	}
	
	public ProductDTO getProductById(Integer productId) throws ResourceNotFoundException {
		Product p = dao.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product ID : "+productId + " not found !"));
		ProductUtil util = new ProductUtil();
		return util.convertToDTO(p);
		
	}
	
	public Product addProduct(Product p) {
		Product newProduct = dao.save(p);
		return newProduct;
	}
	
	public void deleteProduct(Integer id) throws ResourceNotFoundException{
		dao.deleteById(id);
	}
	
	public boolean getProductByName(String productName) throws ResourceNotFoundException {
		Optional<Product> p = dao.findByName(productName);
		boolean isSameNamePresent = false;
		if(p.get() != null)
			isSameNamePresent = true;
		return isSameNamePresent;
			
	}
	
	public List<Product> getAllProductByName(String productName) throws ResourceNotFoundException {
		List<Product> productList = dao.findByNameContainingIgnoreCase(productName);
		if(productList != null && productList.size() == 0)
			throw new ResourceNotFoundException("Product Name : "+ productName + " ");
		return productList;
	}
	
	public Product updateProduct(ProductDTO dto) throws ResourceNotFoundException {
		ProductUtil util = new ProductUtil();
		Product p = util.convertToEntity(dto);
		Product updatedObject =dao.saveAndFlush(p);
		if(updatedObject == null)
			throw new ResourceNotFoundException("Sorry, updatation failed");
		return updatedObject;
	}
	
	@Transactional
	public boolean updateProductQuantity(UpdateProductDTO dto) throws ResourceNotFoundException {
		boolean update = true;
		if(dto != null && dto.getListToUpdate() != null && !dto.getListToUpdate().isEmpty()) {
			for(UpdateProductListDTO u : dto.getListToUpdate()) {
				dao.updateProductDTO(u.getQuantity(), u.getId());
			}
		} else
			update = false;
		return update;
	}
	
	public List<Product> getRawMaterialByFinalProduct(Integer finalProductId) throws ResourceNotFoundException {
		List<Product> productList = dao.findByFinalProduct(finalProductId);
		if(productList != null && productList.size() == 0)
			throw new ResourceNotFoundException("findByFinalProduct "+ finalProductId + " ");
		return productList;
	}
	
	
}
