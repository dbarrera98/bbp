package com.cobistopaz.invoice.file.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.cobistopaz.invoice.file.processor.repository")
public class InvoiceFileProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceFileProcessorApplication.class, args);
    }

}