package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {

    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService = new CustomerServiceImpl();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to Customer Management System");
            System.out.println("1. Display All Customers");
            System.out.println("2. Insert a New Customer");
            System.out.println("3. Update an Existing Customer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    displayAllCustomers();
                    break;
                case 2:
                    insertNewCustomer();
                    break;
                case 3:
                    updateExistingCustomer();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        System.out.println("Exiting the application. Goodbye!");
        scanner.close();
    }

    private static void displayAllCustomers() {
        ArrayList<Customer> customerList = customerService.getAllcustomers();
        
        System.out.printf("%-12s %-15s %-15s %-30s %-15s %-15s%n", "CustomerCode", "First Name", "Last Name", "Address", "Phone Number", "Aadhaar No");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (Customer customer : customerList) {
            System.out.printf("%-12d %-15s %-15s %-30s %-15d %-15d%n", 
                customer.getCustomerCode(), 
                customer.getCustomerFirstname(), 
                customer.getCustomerLastname(), 
                customer.getAddress(), 
                customer.getPhNumber(), 
                customer.getAadharNo());
        }
    }

    private static void insertNewCustomer() {
        System.out.println("Enter customer details:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Phone Number: ");
        long phoneNumber = scanner.nextLong();
        System.out.print("Aadhaar Number: ");
        long aadhaarNumber = scanner.nextLong();

        Customer newCustomer = new Customer(0, firstName, lastName, address, phoneNumber, aadhaarNumber);
        int rowsAffected = customerService.insertIntoCustomer(newCustomer);
        if (rowsAffected > 0) {
            System.out.println("Customer inserted successfully.");
        } else {
            System.out.println("Failed to insert customer.");
        }
    }

    private static void updateExistingCustomer() {
        System.out.print("Enter Customer Code to update: ");
        int customerCode = scanner.nextInt();
        scanner.nextLine(); 

        Customer existingCustomer = findCustomerByCode(customerCode);
        if (existingCustomer == null) {
            System.out.println("Customer not found with code: " + customerCode);
            return;
        }

        System.out.println("\nSelect field to update:");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. Phone Number");
        System.out.println("5. Aadhaar Number");
        System.out.print("Enter your choice: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine(); 

        switch (fieldChoice) {
            case 1:
                System.out.print("Enter new First Name: ");
                String firstName = scanner.nextLine();
                existingCustomer.setCustomerFirstname(firstName);
                break;
            case 2:
                System.out.print("Enter new Last Name: ");
                String lastName = scanner.nextLine();
                existingCustomer.setCustomerLastname(lastName);
                break;
            case 3:
                System.out.print("Enter new Address: ");
                String address = scanner.nextLine();
                existingCustomer.setAddress(address);
                break;
            case 4:
                System.out.print("Enter new Phone Number: ");
                long phoneNumber = scanner.nextLong();
                existingCustomer.setPhNumber(phoneNumber);
                break;
            case 5:
                System.out.print("Enter new Aadhaar Number: ");
                long aadhaarNumber = scanner.nextLong();
                existingCustomer.setAadharNo(aadhaarNumber);
                break;
            default:
                System.out.println("Invalid choice. No updates performed.");
                return;
        }

        int rowsAffected = customerService.updateCustomer(existingCustomer);
        if (rowsAffected > 0) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }

    private static Customer findCustomerByCode(int customerCode) {
        ArrayList<Customer> customerList = customerService.getAllcustomers();
        for (Customer customer : customerList) {
            if (customer.getCustomerCode() == customerCode) {
                return customer;
            }
        }
        return null;
    }
}
