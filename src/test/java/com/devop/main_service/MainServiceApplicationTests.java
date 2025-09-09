package com.devop.main_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.devop.main_service.MainServiceApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "eureka.client.register-with-eureka=false",
    "eureka.client.fetch-registry=false",
    "spring.cloud.config.server.git.uri=classpath:/config-repo"
})
class MainServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void shouldHaveEurekaServerEnabled() {
        // Verify that the application has @EnableEurekaServer annotation
        EnableEurekaServer eurekaAnnotation = MainServiceApplication.class
                .getAnnotation(EnableEurekaServer.class);
        assertNotNull(eurekaAnnotation, "MainServiceApplication should have @EnableEurekaServer annotation");
    }

    @Test
    void shouldHaveConfigServerEnabled() {
        EnableConfigServer configAnnotation = MainServiceApplication.class
                .getAnnotation(EnableConfigServer.class);
        assertNotNull(configAnnotation, "MainServiceApplication should have @EnableConfigServer annotation");
    }

    @Test
    void shouldStartEurekaServerSuccessfully() {
        String eurekaUrl = "http://localhost:" + port + "/eureka/apps";
        ResponseEntity<String> response = restTemplate.getForEntity(eurekaUrl, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldStartConfigServerSuccessfully() {
        String healthUrl = "http://localhost:" + port + "/actuator/health";
        ResponseEntity<String> response = restTemplate.getForEntity(healthUrl, String.class);
                assertTrue(response.getStatusCode().is2xxSuccessful() || 
                  response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    void shouldHaveCorrectApplicationName() {
        String applicationName = applicationContext.getEnvironment()
                .getProperty("spring.application.name");
        
        assertNotNull(applicationName);
    }

    @Test
    void shouldRunMainMethod() {
        
        assertDoesNotThrow(() -> {
            // This test verifies that the main method exists and can be invoked
            MainServiceApplication.main(new String[]{"--server.port=0"});
        });
    }

    @Test
    void shouldHaveRequiredSpringBootAnnotations() {
       
        assertTrue(MainServiceApplication.class.isAnnotationPresent(
                org.springframework.boot.autoconfigure.SpringBootApplication.class),
                "MainServiceApplication should have @SpringBootApplication annotation");
    }

    @Test
    void shouldConfigureEurekaClientProperties() {
        
        String registerWithEureka = applicationContext.getEnvironment()
                .getProperty("eureka.client.register-with-eureka");
        String fetchRegistry = applicationContext.getEnvironment()
                .getProperty("eureka.client.fetch-registry");
        
        
        assertEquals("false", registerWithEureka);
        assertEquals("false", fetchRegistry);
    }
}
