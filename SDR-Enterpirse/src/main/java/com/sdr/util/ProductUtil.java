package com.sdr.util;

import java.util.Date;

import com.sdr.bean.Product;
import com.sdr.bean.ProductDTO;

public class ProductUtil {

	public ProductDTO convertToDTO(Product p) {
		ProductDTO dto = new ProductDTO();
		dto.setName(p.getName());
		dto.setQuantity(p.getQuantity());
		return dto;
	}
	
	public Product convertToEntity(ProductDTO dto) {
		Product p = new Product();
		p.setId(dto.getId());
		p.setName(dto.getName());
		p.setQuantity(dto.getQuantity());
		p.setFinalProduct(dto.getFinalProduct());
		p.setType(dto.getType());
		p.setUpdateDate(new Date());
		return p;
	}
}
