package com.ufps.laboratorioprestamos.infrastructure.config;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime timestamp, int status, String error, String message) {
}
