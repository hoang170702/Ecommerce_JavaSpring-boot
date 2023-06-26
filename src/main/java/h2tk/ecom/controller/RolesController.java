package h2tk.ecom.controller;

import h2tk.ecom.model.Roles;
import h2tk.ecom.model.Users;
import h2tk.ecom.repository.RoleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiRole")
public class RolesController {
    @Autowired
    private RoleRepository RoleRepo;

    @GetMapping("/ListRole")
    public List<Roles> getAllRole(){
        return RoleRepo.findAll();
    }


}
