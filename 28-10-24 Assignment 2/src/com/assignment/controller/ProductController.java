package com.assignment.controller;

import java.util.List;
import java.util.Scanner;
import org.hibernate.SessionFactory;

import com.assignment.model.Product;
import com.assignment.service.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProductController {

	public static void main(String[] args) {
		 
		//step 1
		 //from here i need to reach out to persistence.xml : 
		SessionFactory sessionFactory = (SessionFactory)
						Persistence.createEntityManagerFactory("myecomapp");
		//step 2
		EntityManager entityManager =  sessionFactory.createEntityManager();
		
		//step 3
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Scanner sc = new Scanner(System.in);
		
		ProductService productService = new ProductService(entityManager,entityTransaction);
		while(true) {
			System.out.println("------------Product MENU--------------");
			System.out.println("1. Enter Product in DB");
			System.out.println("2. Fetch All Products");
			System.out.println("3. Delete Product");
			System.out.println("4. Update Product Details");
			System.out.println("0. Exit");
			int input =sc.nextInt();
			if(input == 0 ) {
				System.out.println("Exiting.. thank you");
				break; 
			}
			switch(input) {
			case 1:
				Product product = productService.takeInput(sc);
				productService.insert(product);
				System.out.println("Product added to DB..");
				break; 
            case 2:
                List<Product> products = productService.getAllProducts();
                if (products.isEmpty()) {
                    System.out.println("No products found in the database.");
                } else {
                    System.out.println("Listing all products:");
                    products.forEach(p -> System.out.println(p.toString()));
                }
                break;

            case 3:
                System.out.println("Enter the Product ID to delete: ");
                int productId = sc.nextInt();
                productService.deleteProduct(productId);
                break;
            
            case 4:
                System.out.println("Enter Product ID to update:");
                int updateId = sc.nextInt();
                Product productToUpdate = productService.getProductById(updateId);
                if (productToUpdate != null) {
                    System.out.println("Current details: " + productToUpdate);
                    Product updatedProduct = productService.takeInput(sc);
                    updatedProduct.setId(updateId); // Set the ID for the update
                    productService.updateProduct(updatedProduct);
                    System.out.println("Product updated successfully.");
                } else {
                    System.out.println("Product not found with ID: " + updateId);
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
