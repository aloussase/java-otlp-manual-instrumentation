package io.github.aloussase.otlpdemo.cart;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestBody CartItem cartItem) {
        final var otel = GlobalOpenTelemetry.getTracer("CartController::saveItem");
        final var span = otel.spanBuilder("CartController::saveItem").setSpanKind(SpanKind.SERVER).startSpan();

        try (final var scope = span.makeCurrent()) {
            cartService.saveCartItem(cartItem);
            span.setAttribute("status", "success");
            span.setAttribute("cart_item_id", cartItem.id());
            return ResponseEntity.created(URI.create("/api/v1/cart/" + cartItem.id())).build();
        } finally {
            span.end();
        }
    }

}
