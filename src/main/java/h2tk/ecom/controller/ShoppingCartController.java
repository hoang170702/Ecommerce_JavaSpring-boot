package h2tk.ecom.controller;

import h2tk.ecom.model.Cart;
import h2tk.ecom.model.Products;
import h2tk.ecom.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    private List<Cart> getOrCreateCart(HttpSession session) {
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cart", cartItems);
        }
        return cartItems;
    }
    @GetMapping("/view")
    public ResponseEntity<List<Cart>> viewCart(HttpSession session) {
        List<Cart> cartItems = getOrCreateCart(session);
        return ResponseEntity.ok(cartItems);
    }
    @PostMapping("/add/{productId}")
                public ResponseEntity<String> addToCart(@PathVariable("productId") int productId, HttpSession session) {
                    List<Cart> cartItems = getOrCreateCart(session);

                    Products product = productService.get(productId);
                    if (product != null && product.getId() == productId) {
                        boolean productExists = false;
                        for (Cart item : cartItems) {
                            if (item.getProductId() == productId) {
                    item.setQuantity(item.getQuantity() + 1);
                    productExists = true;
                    break;
                }
            }
            if (!productExists) {
                Cart item = new Cart();
                item.setProductId(product.getId());
                item.setName(product.getName());
                item.setPrice(product.getPrice());
                item.setImage(product.getImage());
                item.setQuantity(1);
                cartItems.add(item);
            }
            session.setAttribute("cart", cartItems);
            return ResponseEntity.ok("Product added to cart successfully!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product added to cart failed!");
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<String> updateCart(@PathVariable int productId,
                                             @RequestParam int quantity,
                                             HttpSession session) {
        List<Cart> cartItems = getOrCreateCart(session);
        for (Cart item : cartItems) {
            if (item.getProductId() == productId) {
                item.setQuantity(quantity);
                return ResponseEntity.ok("Cart item quantity updated successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart!");
    }

    @PostMapping("/remove/{productId}")
    public ResponseEntity<String> removeOneItem(@PathVariable int productId, HttpSession session) {
        List<Cart> cartItems = getOrCreateCart(session);
        Iterator<Cart> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            Cart item = iterator.next();
            if (item.getProductId() == productId) {
                iterator.remove();
                return ResponseEntity.ok("Product removed from cart successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart!");
    }

    @PostMapping("/clearAll")
    public ResponseEntity<String> clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return ResponseEntity.ok("Cart cleared successfully!");
    }

    @GetMapping("/totalQuantity")
    public ResponseEntity<Integer> getTotalQuantity(HttpSession session) {
        List<Cart> cartItems = getOrCreateCart(session);
        int totalQuantity = 0;
        for (Cart item : cartItems) {
            totalQuantity += item.getQuantity();
        }
        return ResponseEntity.ok(totalQuantity);
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<Double> getTotalAmount(HttpSession session) {
        List<Cart> cartItems = getOrCreateCart(session);
        double totalAmount = 0;
        for (Cart item : cartItems) {
            totalAmount += item.getQuantity() * item.getPrice();
        }
        return ResponseEntity.ok(totalAmount);
    }
}
