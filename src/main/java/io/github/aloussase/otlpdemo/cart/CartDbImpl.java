package io.github.aloussase.otlpdemo.cart;

import org.springframework.jdbc.core.JdbcTemplate;


public class CartDbImpl implements CartDb {
    private final JdbcTemplate jdbcTemplate;

    public CartDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        jdbcTemplate.update(
                "insert into cart_item (id, name, price) values (?::decimal, ?, ?)",
                cartItem.id(), cartItem.name(), cartItem.price()
        );
    }
}
