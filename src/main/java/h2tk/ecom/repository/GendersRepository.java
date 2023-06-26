package h2tk.ecom.repository;

import h2tk.ecom.model.Genders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GendersRepository extends JpaRepository<Genders,Integer> {
}
