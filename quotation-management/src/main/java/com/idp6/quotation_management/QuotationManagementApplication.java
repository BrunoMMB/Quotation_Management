package com.idp6.quotation_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.idp6.quotation_management.model.Quote;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class QuotationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationManagementApplication.class, args);
	}

}
