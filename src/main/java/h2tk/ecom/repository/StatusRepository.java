package h2tk.ecom.repository;


import h2tk.ecom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
