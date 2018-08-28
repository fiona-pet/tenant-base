package com.fionapet.tenant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Tenant {
	
	@Id
	private Long id;
	
	@NotBlank
	@Column(nullable=false, unique=true)
	private String name;
	
	@NotBlank
	@Column(nullable=false)
	private String schema;
	
	public Tenant() {
		
	}
	
	public Tenant(String schema) {
		this.schema = schema;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", name=" + name + ", schema=" + schema + "]";
	}

}
