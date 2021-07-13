package com.retail.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"isActive","role"})
@Entity
@Table(name = "login")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "password")
	private String password;
	@JsonIgnore
	private boolean isActive;
	@JsonIgnore
	private String role;
	
	public Login(String password,boolean isActive,String role){
		this.password=password;		
		this.isActive=isActive;
		this.role=role;
	}
	

}
