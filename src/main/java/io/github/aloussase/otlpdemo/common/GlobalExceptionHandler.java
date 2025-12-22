package io.github.aloussase.otlpdemo.common;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDatabaseException(
            HttpServletRequest req,
            DuplicateKeyException ex
    ) {
        final var otel = GlobalOpenTelemetry.getTracer("GlobalExceptionHandler::handleDatabaseException");
        final var span = otel
                .spanBuilder("GlobalExceptionHandler::handleDatabaseException")
                .setSpanKind(SpanKind.SERVER).startSpan();

        try (final var scope = span.makeCurrent()) {
            span.setAttribute("status", "error");
            span.setAttribute("message", ex.getMessage());
            span.recordException(ex);
        } finally {
            span.end();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


}
