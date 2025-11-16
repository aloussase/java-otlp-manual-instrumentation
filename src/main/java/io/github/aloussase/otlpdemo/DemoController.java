package io.github.aloussase.otlpdemo;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    // TODO: Implement a fibonacci endpoint

    // TODO: Add a postgres db to see what gives

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        final var otel = GlobalOpenTelemetry.get();
        final var tracer = otel.getTracer("healthcheck");

        final var span = tracer.spanBuilder("healthcheck")
                .setSpanKind(SpanKind.CLIENT)
                .startSpan();

        try (final var scope = span.makeCurrent()) {
            span.setAttribute("status", "UP");
            return ResponseEntity.ok("OK");
        } finally {
            span.end();
        }
    }

}
