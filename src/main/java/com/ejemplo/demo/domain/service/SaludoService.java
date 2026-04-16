package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        // SOLUCION RETO (paso 4): se normaliza y valida el nombre antes de responder.
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!"
                .formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    private String normalizarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        nombre = nombre.trim();

        if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no debe contener números");
        }

        return nombre;
    }
}