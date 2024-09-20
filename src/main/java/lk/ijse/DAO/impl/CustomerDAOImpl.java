package lk.ijse.DAO.impl;

import lk.ijse.DAO.custome.CustomerDAO;
import lk.ijse.Entity.Customer;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
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
}
