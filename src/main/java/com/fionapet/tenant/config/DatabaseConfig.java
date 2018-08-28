package com.fionapet.tenant.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.fionapet.tenant.domain.Tenant;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.fionapet.tenant.FionaTenantApp;
import com.fionapet.tenant.repository.TenantRepository;

import liquibase.integration.spring.MultiTenantSpringLiquibase;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class DatabaseConfig {
	
	@Autowired
    private JpaProperties jpaProperties;
	
	@Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
																		MultiTenantConnectionProvider multiTenantConnectionProvider,
																		CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
		Map<String, Object> jpaPropertiesMap = new HashMap<>();
		jpaPropertiesMap.putAll(jpaProperties.getProperties());
		jpaPropertiesMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
		jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(FionaTenantApp.class.getPackage().getName());
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaPropertyMap(jpaPropertiesMap);
		
		return em;
	}
	
	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:/db/changelog/db.changelog-core.xml");
		liquibase.setDefaultSchema("cboard");
		liquibase.setShouldRun(true);
		
		return liquibase;
	}
	
	@Bean
	public MultiTenantSpringLiquibase liquibaseMultiTenant(DataSource dataSource, TenantRepository tenantRepository) {
		MultiTenantSpringLiquibase liquibase = new MultiTenantSpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:/db/changelog/db.changelog-tenants.xml");
		List<String> schemas = tenantRepository.findAll().stream().map(
				Tenant::getSchema).collect(Collectors.toList());
		liquibase.setSchemas(schemas);
		liquibase.setShouldRun(true);			
		
		return liquibase;
	}
}
