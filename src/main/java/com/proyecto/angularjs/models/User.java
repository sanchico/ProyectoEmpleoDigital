package com.proyecto.angularjs.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "User")
public class User implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3985614472072622616L;

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private @Id @GeneratedValue Long id_user;

	@Column(name = "name")
	private String name;

	private String role;

	private String password;

	@OneToMany(mappedBy="User_id",cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	private List<Booking> bookingsUser = new ArrayList<>();

	protected User() {
	}

	public User(String name, String password, String role) {
		this.name = name;
		this.setPassword(password);
		this.role = role;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
//	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * @return the id
	 */

	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	public Long getId_user() {
		return id_user;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public List<Booking> getBookingsUser() {
		return bookingsUser;
	}

	public void setBookingsUser(List<Booking> bookingsUser) {
		this.bookingsUser = bookingsUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingsUser == null) ? 0 : bookingsUser.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (bookingsUser == null) {
			if (other.bookingsUser != null)
				return false;
		} else if (!bookingsUser.equals(other.bookingsUser))
			return false;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", name=" + name + ", role=" + role + ", password=" + password
				+ ", bookingsUser=" + bookingsUser + "]";
	}

	

	


}