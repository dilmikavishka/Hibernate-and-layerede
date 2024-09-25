package lk.ijse.DAO.impl;

import lk.ijse.DAO.custome.CustomerDAO;
import lk.ijse.Entity.Customer;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public  boolean delete(String id){
        Customer customer = new Customer();
        customer.setId(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(customer);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Customer> customers = session.createQuery("from Customer").list();
        transaction.commit();
        session.close();
        return customers;
    }

    public  boolean save(Customer DTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(DTO);
        transaction.commit();
        session.close();
        return transaction.getStatus().canRollback();
    }

    public  boolean update(Customer DTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(DTO);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId="";

        Object customer = session.createQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1").uniqueResult();
        if (customer!=null) {
            String customerId = customer.toString();
            String prefix = "C";
            String[] split = customerId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
             nextId = prefix + String.format("%03d", ++idNum);

        }else {
            return "C001";
        }

        transaction.commit();
        session.close();
        return nextId;

    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public List<String> getCustomerId() {
        Session session = null;
        Transaction transaction = null;
        List<String> customerIds = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Use HQL to fetch only the customer IDs
            customerIds = session.createQuery("SELECT c.id FROM Customer c", String.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customerIds;

    }
}
