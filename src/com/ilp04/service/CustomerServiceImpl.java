package com.ilp04.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.ilp04.dao.CustomerDao;
import com.ilp04.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDAO;

    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDao();
    }

    @Override
    public ArrayList<Customer> getAllcustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection connection = customerDAO.getConnection();
        customerList = customerDAO.getAllCustomers(connection);
        customerDAO.closeConnection(connection);
        return customerList;
    }

    @Override
    public int insertIntoCustomer(Customer customer) {
        Connection connection = customerDAO.getConnection();
        int rowsAffected = customerDAO.insertCustomer(connection, customer);
        customerDAO.closeConnection(connection);
        return rowsAffected;
    }

    @Override
    public int updateCustomer(Customer customer) {
        Connection connection = customerDAO.getConnection();
        int rowsAffected = customerDAO.updateCustomer(connection, customer);
        customerDAO.closeConnection(connection);
        return rowsAffected;
    }
}
