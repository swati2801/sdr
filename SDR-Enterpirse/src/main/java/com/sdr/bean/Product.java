package com.sdr.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NonNull
	@Column(name = "NAME", unique = true, nullable = false, length = 45)
	private String name;

	@JsonIgnoreProperties(value = {"createDate", "updateDate"}, allowGetters = true)
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateDate;

	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="TYPE")
	private Integer type; //Type = 0(Raw Material) 1= Final Good
	
	@Column(name="FINAL_PRODUCT_ID")
	private Integer finalProduct;
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", quantity=" + quantity + ", type=" + type + ", finalProduct=" + finalProduct + "]";
	}

	public Product(String name, Integer quantity, Integer type, Integer finalProduct) { 
		super(); 
		this.name = name; 
		this.quantity = quantity;
		this.type = type; 
		this.finalProduct = finalProduct; 
	}
	
	public Product() {}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getFinalProduct() {
		return finalProduct;
	}


	public void setFinalProduct(Integer finalProduct) {
		this.finalProduct = finalProduct;
	}

}
