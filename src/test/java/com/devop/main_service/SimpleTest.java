package com.devop.main_service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTest {

    @Test
    void shouldReturnTrue() {
        assertTrue(true);
    }

    @Test
    void shouldCalculateSum() {
        // Given
        int a = 5;
        int b = 3;
        
        // When
        int result = a + b;
        
        // Then
        assertEquals(8, result);
    }
}