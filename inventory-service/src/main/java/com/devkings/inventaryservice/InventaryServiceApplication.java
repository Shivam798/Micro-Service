package com.devkings.inventaryservice;

import com.devkings.inventaryservice.model.Inventary;
import com.devkings.inventaryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventaryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventary inventary = new Inventary();
			inventary.setSkuCode("Iphone 14");
			inventary.setQuantity(14);

			Inventary inventary1 = new Inventary();
			inventary1.setSkuCode("Iphone 14 red");
			inventary1.setQuantity(0);

			inventoryRepository.save(inventary);
			inventoryRepository.save(inventary1);

		};
	}

}
