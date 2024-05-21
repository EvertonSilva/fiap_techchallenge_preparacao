package br.com.lanchonete;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class PreparacaoResourceTest {
    @Test
    void testHealthCheckEndpoint() {
        given()
          .when().get("/api/preparacao/health")
          .then()
             .statusCode(200)
             .body(is("Service is running"));
    }

    @Test
    void testDeveRetornar404PedidoNaoEncontrado() {
        var codigo = UUID.randomUUID().toString();
        var url = String.format("/api/preparacao/%s/status", codigo);

        given()
          .when().get(url)
          .then()
            .statusCode(404);
    }

    @Test
    void testDeveRetornar200PedidoEncontrado() {
        var codigo = "9bff2023-f692-4223-86ec-926c85673fa7";
        var url = String.format("/api/preparacao/%s/status", codigo);

        given()
          .when().get(url)
          .then()
            .statusCode(200);
    }
}
