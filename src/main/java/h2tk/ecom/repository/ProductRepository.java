package h2tk.ecom.repository;


import h2tk.ecom.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findByCategoryName(String categoryName);
}
