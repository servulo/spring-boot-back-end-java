package br.com.sprj.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.sprj.backend.dto.UserDTO;

@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String socialSecurity;
	private String address;
	private String email;
	private String telephone;
	private Date registrationDate;
	private String key;

	public static User convert(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setSocialSecurity(userDTO.getSocialSecurity());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setTelephone(userDTO.getTelephone());
		user.setRegistrationDate(userDTO.getRegistrationDate());
		user.setKey(userDTO.getKey());
		return user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}