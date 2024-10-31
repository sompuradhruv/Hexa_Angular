package com.assignment.controller;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.assignment.model.Vendor;  // Make sure you have a Vendor model class
import com.assignment.service.VendorService; // Make sure you have a VendorService class

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class VendorController {

    public static void main(String[] args) {
        // Step 1: Initialize SessionFactory and EntityManager
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myecomapp");
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Scanner sc = new Scanner(System.in);

        VendorService vendorService = new VendorService(entityManager, entityTransaction);

        while (true) {
            System.out.println("------------Vendor MENU--------------");
            System.out.println("1. Enter Vendor in DB");
            System.out.println("2. Fetch All Vendors");
            System.out.println("3. Delete Vendor");
            System.out.println("4. Update Vendor Details");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Exiting.. thank you");
                break; 
            }
            switch (input) {
                case 1:
                    Vendor vendor = vendorService.takeInput(sc);
                    vendorService.insert(vendor);
                    System.out.println("Vendor added to DB..");
                    break;

                case 2:
                    List<Vendor> vendors = vendorService.getAllVendors();
                    if (vendors.isEmpty()) {
                        System.out.println("No vendors found in the database.");
                    } else {
                        System.out.println("Listing all vendors:");
                        vendors.forEach(v -> System.out.println(v.toString()));
                    }
                    break;

                case 3:
                    System.out.println("Enter the Vendor ID to delete: ");
                    int vendorId = sc.nextInt();
                    vendorService.deleteVendor(vendorId);
                    break;

                case 4:
                    System.out.println("Enter Vendor ID to update:");
                    int updateId = sc.nextInt();
                    Vendor vendorToUpdate = vendorService.getVendorById(updateId);
                    if (vendorToUpdate != null) {
                        System.out.println("Current details: " + vendorToUpdate);
                        Vendor updatedVendor = vendorService.takeInput(sc);
                        updatedVendor.setId(updateId); // Set the ID for the update
                        vendorService.updateVendor(updatedVendor);
                        System.out.println("Vendor updated successfully.");
                    } else {
                        System.out.println("Vendor not found with ID: " + updateId);
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
