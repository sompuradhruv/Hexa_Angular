package com.assignment.controller;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.assignment.model.Category;  // Make sure you have a Category model class
import com.assignment.service.CategoryService; // Make sure you have a CategoryService class

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CategoryController {

    public static void main(String[] args) {
        // Step 1: Initialize SessionFactory and EntityManager
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myecomapp");
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Scanner sc = new Scanner(System.in);

        CategoryService categoryService = new CategoryService(entityManager, entityTransaction);

        while (true) {
            System.out.println("------------Category MENU--------------");
            System.out.println("1. Enter Category in DB");
            System.out.println("2. Fetch All Categories");
            System.out.println("3. Delete Category");
            System.out.println("4. Update Category Details");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Exiting.. thank you");
                break; 
            }
            switch (input) {
                case 1:
                    Category category = categoryService.takeInput(sc);
                    categoryService.insert(category);
                    System.out.println("Category added to DB..");
                    break;

                case 2:
                    List<Category> categories = categoryService.getAllCategories();
                    if (categories.isEmpty()) {
                        System.out.println("No categories found in the database.");
                    } else {
                        System.out.println("Listing all categories:");
                        categories.forEach(c -> System.out.println(c.toString()));
                    }
                    break;

                case 3:
                    System.out.println("Enter the Category ID to delete: ");
                    int categoryId = sc.nextInt();
                    categoryService.deleteCategory(categoryId);
                    break;

                case 4:
                    System.out.println("Enter Category ID to update:");
                    int updateId = sc.nextInt();
                    Category categoryToUpdate = categoryService.getCategoryById(updateId);
                    if (categoryToUpdate != null) {
                        System.out.println("Current details: " + categoryToUpdate);
                        Category updatedCategory = categoryService.takeInput(sc);
                        updatedCategory.setId(updateId); // Set the ID for the update
                        categoryService.updateCategory(updatedCategory);
                        System.out.println("Category updated successfully.");
                    } else {
                        System.out.println("Category not found with ID: " + updateId);
                    }
                    break;

                default: 
                    System.out.println("Invalid Input, Try Again!!");
                    break;
            }
            System.out.println("--------------------------------------");
        }

        sc.close();
    }
}
