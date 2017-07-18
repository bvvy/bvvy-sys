package com.bvvy;

import com.bvvy.basic.repo.impl.BaseRepositoryExtend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryExtend.class, basePackages = {"com.bvvy.sys","com.bvvy.basic"})
public class BvvySysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BvvySysApplication.class, args);
	}
}
