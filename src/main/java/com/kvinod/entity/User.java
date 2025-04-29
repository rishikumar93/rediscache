package com.kvinod.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;


//@Document
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String city;
	private String email;
	private String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
