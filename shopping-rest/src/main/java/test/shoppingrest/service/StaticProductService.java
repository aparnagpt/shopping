package test.shoppingrest.service;

import org.springframework.stereotype.Component;
import test.shoppingrest.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StaticProductService {
    private static final List<Product> PRODUCTS = Arrays.asList(new Product(1, "Apple iPhone 7", 500, "Best smartphone available"), new Product(2, "OnePlus 5t", 300, "Fastest phone"), new Product(3, "Smasung Galaxy S8", 500, "Curved screen"));

    public List<Product> getAllProducts() {
        return PRODUCTS.stream().map(product -> new Product(product.getId(), product.getName(), product.getAmount())).collect(Collectors.toList());
    }

    public Product getProductDetail(int id) {
        for (Product product : PRODUCTS) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
