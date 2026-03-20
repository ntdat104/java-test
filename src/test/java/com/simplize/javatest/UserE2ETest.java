package com.simplize.javatest;

// Spring Boot Test & Port
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

// RestAssured Core Imports (Static)
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*; // Dùng cho các hàm so sánh trong .then() nếu cần

// E2E Test (RestAssured – chạy full app)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserE2ETest {

    @LocalServerPort
    int port;

    @Test
    void full_flow_test() {
        Long userId =
                given()
                        .port(port)
                        .contentType("application/json")
                        .body("{\"name\":\"Test\",\"email\":\"test@gmail.com\"}")
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(200)
                        .extract().path("id");

        given()
                .port(port)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200);
    }
}