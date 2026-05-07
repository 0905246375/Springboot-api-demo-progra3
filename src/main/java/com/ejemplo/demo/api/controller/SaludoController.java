package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SaludoRequest;
import com.ejemplo.demo.domain.service.SaludoService;
import com.ejemplo.demo.generated.api.SaludosApi;
import com.ejemplo.demo.generated.api.WorkshopApi;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaludoController implements WorkshopApi, SaludosApi {

    // Inyección por constructor
    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }
    @Override
    public java.util.Optional<org.springframework.web.context.request.NativeWebRequest> getRequest() {
        return java.util.Optional.empty();
    }

    // GET /api/v1
    @Override
    public ResponseEntity<Object> getWorkshopHealth() {

        return ResponseEntity.ok(Map.of(
                "estado", "ok",
                "mensaje", "Workshop Spring Boot activo"
        ));
    }

    // GET /api/v1/saludos
    @Override
    public ResponseEntity<Object> saludarPorGet(String nombre) {

        return ResponseEntity.ok(
                saludoService.crearSaludo(nombre)
        );
    }

    // POST /api/v1/saludos
    @Override
    public ResponseEntity<Object> saludarPorPost(Object body) {

        Map<String, Object> data = (Map<String, Object>) body;

        String nombre = (String) data.get("nombre");

        if (nombre == null || nombre.trim().isEmpty()) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "codigo", "VALIDATION_ERROR",
                            "mensaje", "El nombre es obligatorio"
                    )
        );
    }
        return ResponseEntity.ok(
                saludoService.crearSaludo(nombre)
        );
    }
}