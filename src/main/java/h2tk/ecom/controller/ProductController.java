package h2tk.ecom.controller;

import h2tk.ecom.model.Products;
import h2tk.ecom.repository.ProductRepository;
import h2tk.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/apiProduct")
public class ProductController {
    @Autowired
    private ProductRepository ProductRepo;

    @Autowired
    private ProductService productService;

    @GetMapping("/ListProduct")
    public List<Products> listAll() {
        return productService.listAll();
    }

    @GetMapping("/GetProductById/{id}")
    public Products productbyId(@PathVariable int id){
        return productService.get(id);
    }

    @GetMapping("/GetProductByCategory/{nameCategory}")
    public List<Products> ProductbyCategory(@PathVariable String nameCategory){
            return ProductRepo.findByCategoryName(nameCategory);
    }
    @PostMapping("/saveProduct")
    public ResponseEntity<String> Addproduct(@RequestBody @ModelAttribute Products product,
                                             @RequestParam(required = false) MultipartFile imageProduct){
        if (imageProduct != null && imageProduct.getSize() > 0){
        try{
                    File saveFile = new ClassPathResource("/static/images/").getFile();
                    System.out.println(saveFile);
                    String newImageFile = UUID.randomUUID() + ".png";
                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                    Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            product.setImage(newImageFile);
            }catch (Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
            }
        }
        productService.save(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping("/deleteProduct/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try {
            productService.delete(id);
            return ResponseEntity.ok("Product deleted successfully");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") int productId,
                                                @ModelAttribute Products updatedProduct,
                                                @RequestParam(required = false) MultipartFile imageProduct) {
        Products existingProduct = ProductRepo.findById(productId).orElse(null);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        if (imageProduct != null && imageProduct.getSize() > 0) {
            try {
                File saveFile = new ClassPathResource("/static/images/").getFile();
                String newImageFile = UUID.randomUUID() + ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                existingProduct.setImage(newImageFile);
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
            }
        }
        productService.save(existingProduct);
        return ResponseEntity.ok("Product updated successfully");
    }

}
