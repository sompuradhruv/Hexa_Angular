package com.assignment.service;

import java.util.List;

import java.util.Scanner;

import com.assignment.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProductService {

	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public ProductService(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.entityManager = entityManager;
		this.entityTransaction = entityTransaction;
	}


	public Product takeInput(Scanner sc) {
		Product product = new Product();
		System.out.println("Enter Product Title");
		sc.nextLine();
		product.setTitle(sc.nextLine());
		System.out.println("Enter description");
		product.setDescription(sc.nextLine());
		System.out.println("Enter price");
		product.setPrice(sc.nextDouble());
		return product;
	}
	
	public void insert(Product product) {
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
	}
	
    public void deleteProduct(int productId) {
        entityTransaction.begin();
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            entityManager.remove(product);
            System.out.println("Product with ID " + productId + " deleted successfully.");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
        entityTransaction.commit();
    }

    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }
    
    public Product getProductById(int productId) {
        return entityManager.find(Product.class, productId);
    }

    public void updateProduct(Product product) {
        entityTransaction.begin();
        entityManager.merge(product);
        entityTransaction.commit();
    }


}