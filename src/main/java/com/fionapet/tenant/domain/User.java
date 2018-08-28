package com.fionapet.tenant.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	private String name;
	
	@NotBlank
	@Email
	@Column(nullable=false, unique=true)
	private String email;
	
	@NotBlank
	@Column(nullable=false, unique=true)
	private String username;
	
	@NotBlank
	@Column(nullable=false)
	@JsonIgnore
	private String password;
	
	@Column
	private boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
		joinColumns={@JoinColumn(name="id_user", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="id_role", referencedColumnName="id")}			
	)
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(optional=true)
	@JoinColumn(name="id_tenant")
	private Tenant tenant;
	
	public User() {
		
	}
	
	public User(String name, String email, String username, String password) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", active="
				+ active + ", tenant=" + tenant + "]";
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}
	
}
