package h2tk.ecom.repository;

import h2tk.ecom.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
