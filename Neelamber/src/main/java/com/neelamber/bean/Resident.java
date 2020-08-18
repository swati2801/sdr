package com.neelamber.bean;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resident")
@EntityListeners(AuditingEntityListener.class)
public class Resident {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NonNull
	@Column(name = "NAME", unique = true, nullable = false, length = 45)
	private String name;
	
	@NonNull
	@Column(name="FLAT_NO", unique = true, nullable = false)
	private String flatNo;
	
	@Column(name="TELEPHONE1")
	private String telephone1;
	
	@Column(name="TELEPHONE2")
	private String telephone2;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CAR_NO")
	private String carNo;
	
	@Column(name="DATE_OF_SHIFT")
	private Date shiftDate  = new Date();
	
	@Column(name="NO_OF_MEMBER")
	private Integer no_of_member;
	
	@JsonIgnoreProperties(value = {"createDate", "updateDate"}, allowGetters = true)
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateDate;

	
	public Resident(String name2, String flatNo2, String telephone12, String telephone22, String carNo2,
			String email2) {
		super();
		this.name = name2;
		this.flatNo = flatNo2;
		this.telephone1 = telephone12;
		this.telephone2 = telephone22;
		this.carNo = carNo2;
		this.email = email2;
	}

}
