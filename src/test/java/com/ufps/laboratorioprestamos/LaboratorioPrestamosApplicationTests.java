package com.ufps.laboratorioprestamos;

import com.ufps.laboratorioprestamos.infrastructure.config.LaboratorioPrestamosApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LaboratorioPrestamosApplication.class)
@ActiveProfiles("test")
class LaboratorioPrestamosApplicationTests {

    @Test
    void contextLoads() {
    }
}

