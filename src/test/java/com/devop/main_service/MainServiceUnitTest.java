package com.devop.main_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para MainServiceApplication
 * Usa @ExtendWith(MockitoExtension.class) sem contexto Spring
 */
@ExtendWith(MockitoExtension.class)
class MainServiceUnitTest {

    @Test
    void shouldCreateMainServiceApplication() {
        // Given
        MainServiceApplication application = new MainServiceApplication();
        
        // Then
        assertNotNull(application);
    }
    
    @Test
    void shouldHaveMainMethod() throws NoSuchMethodException {
        // When
        var mainMethod = MainServiceApplication.class.getMethod("main", String[].class);
        
        // Then
        assertNotNull(mainMethod);
        assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
    }
}