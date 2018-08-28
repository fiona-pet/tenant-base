package com.fionapet.tenant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@NotBlank
	@Column(nullable=false, unique=true)
	private String name;
	
	public Role() {
		
	}
	
	public Role(String nome) {
		this.name = nome;
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

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}
