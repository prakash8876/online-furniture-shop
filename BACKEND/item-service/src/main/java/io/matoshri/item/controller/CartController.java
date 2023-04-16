package io.matoshri.item.controller;

import io.matoshri.item.entity.CartDetails;
import io.matoshri.item.service.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add-to-cart/{customer-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomerItemsToCart(@PathVariable("customer-id")final Long customerId,
                                       @RequestBody CartDetails cartDetails) {
        cartService.saveCart(cartDetails);
    }

    @PostMapping("/place-order/{cart-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrderOrCancelOrder(@PathVariable("cart-id") final Long cartId,
                                @RequestParam(required = true, name = "status", defaultValue = "confirm") String status) {
        return cartService.placeOrderOrCancelOrder(cartId, status);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CartDetails sample() {
        CartDetails details = new CartDetails();
        details.setId(100L);
        details.setCustomerId(4545L);

        Map<String,Integer> map = new HashMap<>();
        map.put("plastic",3);
        map.put("furniture",4);
        map.put("metal",3);

        details.setItems(map);

        return details;
    }
}
