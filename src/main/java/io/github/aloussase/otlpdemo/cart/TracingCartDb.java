package io.github.aloussase.otlpdemo.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;

public class TracingCartDb implements CartDb {
    private final CartDb inner;
    private final ObjectMapper mapper;

    public TracingCartDb(CartDb inner, ObjectMapper mapper) {
        this.inner = inner;
        this.mapper = mapper;
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        final var otel = GlobalOpenTelemetry.get();

        // Instrumentation scopes: https://opentelemetry.io/docs/concepts/instrumentation-scope/
        final var tracer = otel.getTracer("TracingCartDb::saveCartItem");

        final var span = tracer.spanBuilder("TracingCartDb::saveCartItem")
                // Span kinds: https://opentelemetry.io/docs/concepts/signals/traces/#span-kind
                .setSpanKind(SpanKind.SERVER)
                .startSpan();

        try (final var scope = span.makeCurrent()) {
            span.setAttribute("cart_item", mapper.writeValueAsString(cartItem));
            inner.saveCartItem(cartItem);
        } catch (JsonProcessingException ignored) {
            inner.saveCartItem(cartItem);
        } finally {
            // Span does not implement Autocloseable:
            // https://github.com/open-telemetry/opentelemetry-java/issues/4299
            span.end();
        }
    }
}
