package com.simplize.javatest;

// JUnit 5 Jupiter Imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// Mockito Core & Extension Imports
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Static Imports cho Mockito và Assertions (Cực kỳ quan trọng)
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

// Unit Test (Mockito)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void should_create_user_success() {
        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());

        User saved = new User();
        saved.setId(1L);
        saved.setEmail("test@gmail.com");

        when(userRepository.save(any())).thenReturn(saved);

        User result = userService.createUser("Test", "test@gmail.com");

        assertEquals(1L, result.getId());
    }

    @Test
    void should_throw_when_email_exists() {
        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () ->
                userService.createUser("Test", "test@gmail.com"));
    }
}