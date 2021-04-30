package com.forty_percent.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table (indexes = { @Index (columnList = "username"), @Index(columnList = "email") })
public class ApplicationUser implements Serializable{
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@Column (unique=true)
	private String username;
	@Column (unique=true)
	private String email;
	private String passwordEncoded;

	public ApplicationUser(){
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Long getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPasswordEncoded(){
		return passwordEncoded;
	}

	public void setPasswordEncoded(String passwordEncoded){
		this.passwordEncoded = passwordEncoded;
	}
}
