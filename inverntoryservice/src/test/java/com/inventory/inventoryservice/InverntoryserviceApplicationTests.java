package com.inventory.inventoryservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InverntoryserviceApplicationTests {
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
    void shouldReadInventory() {
       var positiveResponse = RestAssured.given()
                .when()
               .get("http://localhost:8082/api/inventory?skuCode=Iphone_16_pro_max&quantity=10")
               .then()
               .log().all()
               .statusCode(200)
               .extract().response().as(Boolean.class);
       assertTrue(positiveResponse);
        var negativeResponse = RestAssured.given()
                .when()
                .get("http://localhost:8082/api/inventory?skuCode=Iphone_16_pro_max&quantity=1000")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);
        assertFalse(negativeResponse);
    }

}
