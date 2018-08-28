package com.fionapet.tenant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fionapet.tenant.audit.AbstractAuditableEntity;

@Entity
public class Demo extends AbstractAuditableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_demo")
	@SequenceGenerator(name="sq_demo", sequenceName="sq_demo", allocationSize=1)
    private Long id;

    @Column
    private String description;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return description;
    }

    public void setDescricao(String descricao) {
        this.description = descricao;
    }

	@Override
	public String toString() {
		return "Demo [id=" + id + ", description=" + description + "]";
	}
	
}
