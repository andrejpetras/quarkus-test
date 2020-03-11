package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitResourceTest extends AbstractTest {

    @DockerService("test")
    DockerComposeService service;

    @Test
    public void testHelloEndpoint() {
        System.out.println("RESULT: " + service.value);
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}