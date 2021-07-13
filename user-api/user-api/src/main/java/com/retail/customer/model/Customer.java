package com.retail.customer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long id;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "first_name")
	private String firstName;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Column(name = "age")
	private Integer age;
	@NotNull
	@Column(name = "gender")
	private String gender;
	@NotNull
	@Column(name = "address")
	private String address;
	@NotNull
	@Column(name = "city")
	private String city;
	@NotNull
	@Column(name = "country")
	private String country;
	private Long income;
	@Email
	@NotNull
	@Column(name = "email")
	private String email;
	@Column(name = "status")
	private String status;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "login_id", referencedColumnName = "id")
	@JsonIgnore
	private Login login;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	@JsonIgnore
	private List<Account> account;

}
