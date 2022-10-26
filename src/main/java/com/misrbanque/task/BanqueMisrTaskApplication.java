package com.misrbanque.task;

import com.misrbanque.task.infrastructure.repositories.EntityManagerRefreshingJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.misrbanque.task")
@EnableJpaRepositories(basePackages = "com.misrbanque.task.repositories", repositoryBaseClass = EntityManagerRefreshingJpaRepository.class)
@EnableTransactionManagement
public class BanqueMisrTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueMisrTaskApplication.class, args);
	}

}
