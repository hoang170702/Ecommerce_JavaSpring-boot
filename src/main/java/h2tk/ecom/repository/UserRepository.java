package h2tk.ecom.repository;

import h2tk.ecom.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);

    Users findByUsernameAndPassword(String username, String password);

    Boolean existsByEmail(String email);

    Users findByEmail(String email);

    Optional<Users> findByUsernameOrEmail(String username, String email);

}
