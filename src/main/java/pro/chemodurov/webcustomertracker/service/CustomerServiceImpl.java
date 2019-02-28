package pro.chemodurov.webcustomertracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.chemodurov.webcustomertracker.dao.CustomerDao;
import pro.chemodurov.webcustomertracker.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    @Transactional
    public void saveOrUpdateCustomer(Customer customer) {
        customerDao.saveOrUpdateCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String searchName) {
        return customerDao.searchCustomers(searchName);
    }
}