package io.matoshri.item.service;

import io.matoshri.item.entity.Cart;
import io.matoshri.item.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
        log.info("Cart saved");
    }
}
