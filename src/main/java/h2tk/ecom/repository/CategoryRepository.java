package h2tk.ecom.repository;

import h2tk.ecom.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Integer> {

}
