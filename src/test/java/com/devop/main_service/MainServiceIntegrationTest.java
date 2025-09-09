package com.devop.main_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de integração básico para Main Service
 * Verifica se o contexto Spring carrega corretamente
 */
@SpringBootTest(classes = MainServiceApplication.class)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class MainServiceIntegrationTest {

    @Test
    void contextLoads() {
        // Este teste verifica se o contexto Spring carrega sem erros
        // Se chegou até aqui, o contexto foi carregado com sucesso
        assertTrue(true, "Contexto Spring carregado com sucesso");
    }

    @Test
    void applicationShouldStart() {
        // Verifica se a aplicação pode ser instanciada
        MainServiceApplication app = new MainServiceApplication();
        assertNotNull(app, "Aplicação deve ser instanciada corretamente");
    }

    @Test
    void mainMethodExists() throws NoSuchMethodException {
        // Verifica se o método main existe e é público/estático
        var mainMethod = MainServiceApplication.class.getMethod("main", String[].class);
        assertNotNull(mainMethod, "Método main deve existir");
        assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()), "Método main deve ser estático");
        assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()), "Método main deve ser público");
    }
}