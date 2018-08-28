package com.fionapet.tenant.repository;

import java.util.Optional;

import com.fionapet.tenant.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	Optional<Tenant> findByName(String name);
	
	Optional<Tenant> findBySchema(String schema);
	
}
