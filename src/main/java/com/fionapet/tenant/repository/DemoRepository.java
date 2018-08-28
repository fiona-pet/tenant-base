package com.fionapet.tenant.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fionapet.tenant.domain.Demo;

@Repository
public interface DemoRepository extends PagingAndSortingRepository<Demo, Long>, JpaSpecificationExecutor<Demo> {

}
