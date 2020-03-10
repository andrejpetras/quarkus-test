package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class Fruit2ResourceTest extends AbstractTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testHelloEndpoint2() {
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }
}