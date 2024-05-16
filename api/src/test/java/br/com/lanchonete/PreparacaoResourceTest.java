package br.com.lanchonete;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PreparacaoResourceTest {
    @Test
    void testHealthCheckEndpoint() {
        given()
          .when().get("/preparacao/health")
          .then()
             .statusCode(200)
             .body(is("Service is running"));
    }
}
