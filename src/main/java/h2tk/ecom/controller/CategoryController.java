package h2tk.ecom.controller;

import h2tk.ecom.model.Categories;
import h2tk.ecom.model.Users;
import h2tk.ecom.repository.CategoryRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiCategories")
public class CategoryController {

    @Autowired
    private CategoryRepository CateRepo;

    public Users getUserBySession(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        return users;
    }

    @GetMapping("/ListCategory")
    public List<Categories> listAll(HttpSession session) {
        Users users = getUserBySession(session);
        return CateRepo.findAll();
    }

    @PostMapping("/saveCategory")
    public Categories Addcategory(@RequestBody Categories category){
        return CateRepo.save(category);
    }

    @GetMapping("/deleteCategory/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try {
            CateRepo.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete category");
        }
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestBody Categories category){
        try{
            Categories exitCategory = CateRepo.findById(id).orElse(null);
            if(exitCategory == null){
                return ResponseEntity.notFound().build();
            }
            exitCategory.setName(category.getName());
            CateRepo.save(exitCategory);
            return ResponseEntity.ok("Category updated successfully");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update category");
        }
    }
}
