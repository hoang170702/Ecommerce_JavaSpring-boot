package h2tk.ecom.repository;

import h2tk.ecom.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
}
