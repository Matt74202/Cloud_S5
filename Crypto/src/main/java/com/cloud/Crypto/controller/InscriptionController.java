package com.cloud.Crypto.controller;
import org.springframework.web.bind.annotation.*;
import com.cloud.Crypto.services.InscriptionService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class InscriptionController {
    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean removed = productService.deleteProduct(id);
        return removed ? "Product deleted" : "Product not found";
    }
}


