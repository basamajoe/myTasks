package com.javalabs.web.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idUser")
	private long idUser;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 45, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "username")
	private String username;
	@JsonIgnore
	@NotBlank(groups = {FormValidationGroup.class })
	@Size(min = 5, max = 50, groups = { FormValidationGroup.class })
	private String password;
	@Email
	@NotBlank
	private String email;
	private String authority;
	private Boolean enabled = true;
	@Size(min = 3, max = 6, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	private String aka = "";
	private Date timestamp;
	@ManyToMany(cascade = { CascadeType.ALL })
	@JsonIgnore
	@JoinTable(name = "r_userrole", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = { @JoinColumn(name = "idRole") })
	private Set<Role> roles = new HashSet<Role>();
	
	public User() {
	}

	public User(long idUser) {
		this.idUser = idUser;
	}

	public User(String username, String password, String email,
			boolean enabled, String authority, String aka) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
		this.aka = aka;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdUser() {
		return idUser;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return this.authority;		
	}

	public void setAuthority(String authority) {
		this.authority = authority;		
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setTimestamp(Date tm) {
		this.timestamp = tm;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aka == null) ? 0 : aka.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (aka == null) {
			if (other.aka != null)
				return false;
		} else if (!aka.equals(other.aka))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", enabled="
				+ enabled + ", aka=" + aka + ", timestamp=" + timestamp + "]";
	}

}
