package com.offliner.offliner.controller;

import com.offliner.offliner.model.Cart;
import java.util.List;
import com.offliner.offliner.model.Product;
import com.offliner.offliner.model.User;
import com.offliner.offliner.repository.CartRepos;
import com.offliner.offliner.repository.ProductRepos;
import com.offliner.offliner.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepos cartRepository;

    @Autowired
    private UserRepos userRepository;

    @Autowired
    private ProductRepos productRepository;

    // Получить все товары в корзине пользователя
    @GetMapping
    public List<Cart> getCartItems(@RequestParam("userId") Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Добавить товар в корзину
    @PostMapping
    public Cart addProductToCart(@RequestBody Cart cart) {
        if (cart.getUser() == null || cart.getProduct() == null) {
            throw new RuntimeException("Пользователь или продукт не указаны");
        }

        // Загружаем пользователя из базы по его ID
        User user = userRepository.findById(cart.getUser().getId()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // Загружаем товар по его ID
        Product product = productRepository.findById(cart.getProduct().getId()).orElseThrow(() -> new RuntimeException("Продукт не найден"));

        // Проверяем, существует ли уже такой товар в корзине у данного пользователя
        Cart existingCartItem = cartRepository.findByUserIdAndProductId(user.getId(), product.getId());

        if (existingCartItem != null) {
            // Если товар уже есть в корзине, увеличиваем количество
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cart.getQuantity());
            return cartRepository.save(existingCartItem);
        }

        // Если товара еще нет в корзине, сохраняем новый
        cart.setUser(user); // Устанавливаем пользователя в корзину
        cart.setProduct(product); // Устанавливаем товар в корзину
        return cartRepository.save(cart);
    }


    // Обновить количество товара в корзине
    @PutMapping("/{id}")
    public Cart updateCartItem(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.setQuantity(updatedCart.getQuantity());
        return cartRepository.save(cart);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestParam("userId") Long userId) {
        // Получаем все товары в корзине пользователя
        List<Cart> userCartItems = cartRepository.findByUserId(userId);

        // Удаляем все товары из корзины пользователя
        for (Cart cartItem : userCartItems) {
            cartRepository.delete(cartItem);
        }

        // Здесь можно также добавить логику, если нужно отправить уведомление или выполнить другие действия
    }



    // Удалить товар из корзины
    @DeleteMapping("/{id}")
    public void removeCartItem(@PathVariable Long id) {
        cartRepository.deleteById(id);
    }
}

