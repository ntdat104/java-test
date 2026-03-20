//package com.simplize.javatest;
//
//// JUnit 5 & Spring Boot Test
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
//import org.springframework.test.context.bean.override.mockito.MockitoBean; // Chỉ dành cho Spring Boot 3.4+
//import org.springframework.test.web.servlet.MockMvc;
//
//// Các Static Import cực kỳ quan trọng cho MockMvc và Mockito
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.any;
//
//// Thư viện hỗ trợ
//import org.springframework.http.MediaType;
//
//// Controller Test (MockMVC)
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private UserService userService;
//
//    @Test
//    void should_create_user() throws Exception {
//        User user = new User();
//        user.setId(1L);
//
//        when(userService.createUser(any(), any()))
//                .thenReturn(user);
//
//        mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\":\"Test\",\"email\":\"test@gmail.com\"}"))
//                .andExpect(status().isOk());
//    }
//}