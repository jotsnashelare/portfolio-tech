package org.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tech.entity.Category;
import org.tech.entity.Product;
import org.tech.dto.ProductResponse;
import org.tech.service.ProductService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        try {
            Page<Product> products = productService.getAllProducts(pageable);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching products", e);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("pid") Integer pid) {
        try {
            Product product = productService.getProductById(pid);
            ProductResponse response = new ProductResponse();
            response.setPid(product.getPid());
            response.setPname(product.getPname());
            response.setPprice(product.getPprice());
            Category category = product.getCategory();
            if (category != null) {
                response.setCid(category.getCid());
                response.setCname(category.getCname());
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found", e);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (product.getCategory() == null || product.getCategory().getCid() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product must have a valid Category ID");
        }
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating product", e);
        }
    }

    @PutMapping("/{pid}")
    public ResponseEntity<Product> updateProduct(@PathVariable("pid") Integer pid, @RequestBody Product newProductDetails) {
        try {
            Product updatedProduct = productService.updateProduct(pid, newProductDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or update failed", e);
        }
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable("pid") Integer pid) {
        try {
            String message = productService.deleteProduct(pid);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or deletion failed", e);
        }
    }
}
