package com.fionapet.tenant.config;

import com.fionapet.tenant.audit.UserAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
	
	@Bean
    public AuditorAware<Long> auditorProvider() {
        return new UserAuditorAware();
    }

}
