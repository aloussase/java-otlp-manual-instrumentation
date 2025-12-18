package io.github.aloussase.otlpdemo.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartConfiguration {

    @Bean
    public CartDb providesCartDb() {
        // return new CartDbImpl();

        // Using a Decorator to avoid coupling tracing concerns with business logic.

        final var mapper = new ObjectMapper();
        return new TracingCartDb(
                new CartDbImpl(),
                mapper);
    }

    @Bean
    public CartService providesCartService(CartDb cartDb) {
        return new CartService(cartDb);
    }

}
