package com.cashmanager.server.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.cashmanager.server.database.repository",
		"com.cashmanager.server.transaction"})
@ComponentScan("com.cashmanager.server.account.service")
@EnableJpaRepositories(basePackages = "com.cashmanager.server")
@EntityScan("com.cashmanager.server")
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

}
