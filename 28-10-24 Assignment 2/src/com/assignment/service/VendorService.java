package com.assignment.service;

import java.util.List;
import java.util.Scanner;

import com.assignment.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class VendorService {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public VendorService(EntityManager entityManager, EntityTransaction entityTransaction) {
        this.entityManager = entityManager;
        this.entityTransaction = entityTransaction;
    }

    public Vendor takeInput(Scanner sc) {
        Vendor vendor = new Vendor();
        System.out.println("Enter Vendor Name:");
        sc.nextLine(); // Consume newline
        vendor.setName(sc.nextLine());

        System.out.println("Enter Vendor City:");
        vendor.setCity(sc.nextLine());

        System.out.println("Enter User ID:");
        vendor.setUserId(sc.nextInt());

        return vendor;
    }

    public void insert(Vendor vendor) {
        entityTransaction.begin();
        entityManager.persist(vendor);
        entityTransaction.commit();
    }

    public List<Vendor> getAllVendors() {
        Query query = entityManager.createQuery("SELECT v FROM Vendor v", Vendor.class);
        return query.getResultList();
    }

    public Vendor getVendorById(int vendorId) {
        return entityManager.find(Vendor.class, vendorId);
    }

    public void deleteVendor(int vendorId) {
        entityTransaction.begin();
        Vendor vendor = entityManager.find(Vendor.class, vendorId);
        if (vendor != null) {
            entityManager.remove(vendor);
            System.out.println("Vendor with ID " + vendorId + " deleted successfully.");
        } else {
            System.out.println("Vendor with ID " + vendorId + " not found.");
        }
        entityTransaction.commit();
    }

    public void updateVendor(Vendor vendor) {
        entityTransaction.begin();
        entityManager.merge(vendor);
        entityTransaction.commit();
    }
}
