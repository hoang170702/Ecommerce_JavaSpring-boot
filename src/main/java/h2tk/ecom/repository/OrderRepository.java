package h2tk.ecom.repository;

import h2tk.ecom.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
