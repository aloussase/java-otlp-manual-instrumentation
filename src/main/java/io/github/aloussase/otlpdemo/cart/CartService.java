package io.github.aloussase.otlpdemo.cart;

public class CartService {
    private final CartDb cartDb;

    public CartService(CartDb cartDb) {
        this.cartDb = cartDb;
    }

    public void saveCartItem(CartItem cartItem) {
        cartDb.saveCartItem(cartItem);
    }

}
