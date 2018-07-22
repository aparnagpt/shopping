package test.shoppingrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.shoppingrest.model.Product;
import test.shoppingrest.service.DbProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private DbProductService productService;

    @CrossOrigin(origins = "*")
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductDetail(id);
    }

}
