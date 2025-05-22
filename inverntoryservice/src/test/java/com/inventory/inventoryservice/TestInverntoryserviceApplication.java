package com.inventory.inventoryservice;

import org.springframework.boot.SpringApplication;

public class TestInverntoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InverntoryserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
