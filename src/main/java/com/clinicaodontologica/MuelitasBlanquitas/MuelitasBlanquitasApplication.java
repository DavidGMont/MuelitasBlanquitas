package com.clinicaodontologica.MuelitasBlanquitas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MuelitasBlanquitasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MuelitasBlanquitasApplication.class, args);
        LOGGER.info("🦷 Muelitas Blanquitas está abriendo al público...");
    }
}
