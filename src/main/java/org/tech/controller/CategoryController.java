package org.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tech.entity.Category;
import org.tech.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }

    @GetMapping("/{cid}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("cid") Integer cid) {
        return ResponseEntity.ok(categoryService.findCategoryById(cid));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (category.getCname() == null || category.getCname().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name is required");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }

    @PutMapping("/{cid}")
    public ResponseEntity<Category> updateCategory(@PathVariable("cid") Integer cid, @RequestBody Category newCategoryDetails) {
        return ResponseEntity.ok(categoryService.updateCategory(cid, newCategoryDetails));
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("cid") Integer cid) {
        categoryService.deleteCategory(cid);
        return ResponseEntity.noContent().build();
    }
}