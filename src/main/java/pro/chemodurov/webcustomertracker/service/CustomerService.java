package pro.chemodurov.webcustomertracker.service;

import pro.chemodurov.webcustomertracker.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    void saveOrUpdateCustomer(Customer customer);
    Customer getCustomer(int id);
    void deleteCustomer(int id);
    List<Customer> searchCustomers(String searchName);
}