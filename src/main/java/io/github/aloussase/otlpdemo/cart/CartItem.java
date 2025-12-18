package io.github.aloussase.otlpdemo.cart;

import java.math.BigDecimal;

public record CartItem(
        String id,
        String name,
        BigDecimal price
) {
}
