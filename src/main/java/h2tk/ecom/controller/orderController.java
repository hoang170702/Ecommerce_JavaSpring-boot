package h2tk.ecom.controller;

import h2tk.ecom.model.*;
import h2tk.ecom.repository.OrderProductRepository;
import h2tk.ecom.repository.OrderRepository;
import h2tk.ecom.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/orderApi")
public class orderController {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private  OrderProductRepository orderProductRepo;
    @Autowired
    private ProductService productService;
    @PostMapping("/orders")
    public ResponseEntity<String> addOrder(HttpSession session){
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cart");
        Users users = (Users) session.getAttribute("user");

        java.util.Date currentDate = new java.util.Date();
        Date sqlDate = new Date(currentDate.getTime());

        if(users != null && cartItems != null && !cartItems.isEmpty()){
            Orders order = new Orders();
            order.setUser(users);
            order.setPain(false);
            order.setOrderDate(sqlDate);
            order.setDelivered(false);
            order.setDeliveryDate(null);
            orderRepo.save(order);

            for (Cart cartItem: cartItems) {
                OrderProduct orderproduct = new OrderProduct();
                Products product = productService.get(cartItem.getProductId());
                orderproduct.setOrder(order);
                orderproduct.setProduct(product);
                orderproduct.setQuantity(cartItem.getQuantity());
                orderproduct.setUnitPrice(cartItem.getPrice());
                orderProductRepo.save(orderproduct);
            }

            session.removeAttribute("cart");
            return ResponseEntity.ok("Order created successfully!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order!");
    }

}
