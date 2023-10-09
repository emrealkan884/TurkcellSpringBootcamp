package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_id")
    private int categoryId;

    @Column(name= "category_name")
    private String categoryName;

    @Column(name= "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
