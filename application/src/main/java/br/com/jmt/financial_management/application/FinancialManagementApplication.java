package br.com.jmt.financial_management.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"br.com.jmt.financial_management"})
@EnableJpaRepositories(basePackages = {"br.com.jmt.financial_management.*"})
@ComponentScan(basePackages = {"br.com.jmt.financial_management.*"})
@EntityScan(basePackages = {"br.com.jmt.financial_management.*"})
public class FinancialManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialManagementApplication.class, args);
    }

}
