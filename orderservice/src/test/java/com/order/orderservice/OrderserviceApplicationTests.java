package com.order.orderservice;

import com.order.orderservice.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
//to pick random port value added as 0
class OrderserviceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.36");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port = port;
	}
	static {
		mySQLContainer.start();
	}
	@Test
	void shouldSubmitOrder() {
		String submitJson = """
				{
				  "skuCode": "Iphone_16_pro_max",
				  "price": 50,
				  "quantity": 12
				}
				    
				""";
		InventoryClientStub.stubInventoryCall("Iphone_16_pro_max",12);
		RestAssured.given()
				.contentType("application/json")
				.body(submitJson)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("skuCode",Matchers.equalTo("Iphone_16_pro_max"))
				.body("price",Matchers.equalTo(50))
				.body("quantity",Matchers.equalTo(12));


	}

}
