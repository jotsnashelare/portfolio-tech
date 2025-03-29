package org.tech.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    private String pname;

    private Double pprice;

    // Many-to-one relation with Category.
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference // Prevents circular references in JSON serialization
    private Category category;

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(Integer pid, String pname, Double pprice, Category category) {
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.category = category;
    }

    // Getters and Setters
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
