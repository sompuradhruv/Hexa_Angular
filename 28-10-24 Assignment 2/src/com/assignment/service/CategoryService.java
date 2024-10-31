package com.assignment.service;

import java.util.List;
import java.util.Scanner;

import com.assignment.model.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CategoryService {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public CategoryService(EntityManager entityManager, EntityTransaction entityTransaction) {
        this.entityManager = entityManager;
        this.entityTransaction = entityTransaction;
    }

    public Category takeInput(Scanner sc) {
        Category category = new Category();
        System.out.println("Enter Category Name:");
        sc.nextLine(); // Consume newline
        category.setName(sc.nextLine());

        System.out.println("Enter Category Sequence:");
        category.setSequence(sc.nextInt());

        return category;
    }

    public void insert(Category category) {
        entityTransaction.begin();
        entityManager.persist(category);
        entityTransaction.commit();
    }

    public List<Category> getAllCategories() {
        Query query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    public Category getCategoryById(int categoryId) {
        return entityManager.find(Category.class, categoryId);
    }

    public void deleteCategory(int categoryId) {
        entityTransaction.begin();
        Category category = entityManager.find(Category.class, categoryId);
        if (category != null) {
            entityManager.remove(category);
            System.out.println("Category with ID " + categoryId + " deleted successfully.");
        } else {
            System.out.println("Category with ID " + categoryId + " not found.");
        }
        entityTransaction.commit();
    }

    public void updateCategory(Category category) {
        entityTransaction.begin();
        entityManager.merge(category);
        entityTransaction.commit();
    }
}
