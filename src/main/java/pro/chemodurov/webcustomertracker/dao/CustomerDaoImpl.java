package pro.chemodurov.webcustomertracker.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pro.chemodurov.webcustomertracker.entity.Customer;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> getAllQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
        return getAllQuery.getResultList();
    }

    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query deleteCustomerQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        deleteCustomerQuery.setParameter("customerId", id);
        deleteCustomerQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> theQuery;
        if (searchName != null && searchName.trim().length() > 0) {
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }
        else {
            theQuery =currentSession.createQuery("from Customer", Customer.class);
        }
        return theQuery.getResultList();
    }
}