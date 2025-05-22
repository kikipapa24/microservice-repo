package com.inventory.inventoryservice;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {

		return new MySQLContainer<>(DockerImageName.parse("mysql:8.0.36"))
				.withDatabaseName("inventoryservice")
				.withPassword("password")
				.withCommand("--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci");

	}
}
