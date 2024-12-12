package com.jeenit.portfolio;

import com.jeenit.portfolio.audit.BaseEntityAuditAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.jeenit.portfolio.repository")
@EntityScan("com.jeenit.portfolio.model")
@EnableJpaAuditing(auditorAwareRef = "baseEntityAuditAware")
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

}
