package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PrestamoRequest(

        @NotNull
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
        BigDecimal monto,

        @NotNull
        @DecimalMin(value = "0.01", message = "La tasa debe ser mayor a 0")
        BigDecimal tasaAnual,

        @Min(value = 1, message = "Meses mínimo 1")
        @Max(value = 360, message = "Meses máximo 360")
        int meses
) {}
