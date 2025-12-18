package io.github.aloussase.otlpdemo.cart;

import java.util.concurrent.ConcurrentHashMap;


public class CartDbImpl implements CartDb {
    private final ConcurrentHashMap<String, CartItem> cartItems = new ConcurrentHashMap<>();

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItems.put(cartItem.id(), cartItem);
    }
}
