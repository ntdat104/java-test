package com.simplize.javatest;

// JUnit 5 & Spring Boot Test
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Testcontainers Core & MySQL
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// Assertions & Models (Giả định cấu trúc project của bạn)
import static org.junit.jupiter.api.Assertions.assertEquals;

// Integration Test (DB thật với Testcontainers)
@SpringBootTest
@Testcontainers
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true"
})
class UserIntegrationTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        // Đảm bảo dùng đúng Driver cho MySQL
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
    }

    @Autowired
    private UserService userService;

    @Test
    void should_create_and_get_user() {
        User user = userService.createUser("Test", "test@gmail.com");

        User found = userService.getUser(user.getId());

        assertEquals("Test", found.getName());
    }
}