package com.fionapet.tenant.audit;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@CreatedDate
	@JsonIgnore
	@Column(name="created_date", nullable = false, updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@JsonIgnore
	@Column(name="last_modified_date")
	private LocalDateTime lastModifiedDate;
	
	@CreatedBy
	@JsonIgnore
	@Column(name="created_by", nullable = false, updatable = false)
	private Long createdBy;
	
	@LastModifiedBy
	@JsonIgnore
	@Column(name="last_modified_by")
	private Long lastModifiedBy;

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
