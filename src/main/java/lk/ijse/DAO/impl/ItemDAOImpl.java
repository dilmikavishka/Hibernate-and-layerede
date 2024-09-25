package lk.ijse.DAO.impl;

import lk.ijse.DAO.custome.ItemDAO;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Item> items = session.createQuery("from Item").list();
        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);
        transaction.commit();
        session.close();
        return transaction.getStatus().canRollback();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Item item = new Item();
        item.setItemCode(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(item);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId="";

        Object item = session.createQuery("SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1").uniqueResult();
        if (item!=null) {
            String ItemCode = item.toString();
            String prefix = "I";
            String[] split = ItemCode.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        }else {
            return "I001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }

    @Override
    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, id);
        transaction.commit();
        session.close();
        return item;
    }


    @Override
    public List<String> getItemCode() {
        Session session = null;
        Transaction transaction = null;
        List<String> itemCode = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Use HQL to fetch only the customer IDs
            itemCode = session.createQuery("SELECT i.id FROM Item i", String.class).list();

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
        return itemCode;
    }
}
