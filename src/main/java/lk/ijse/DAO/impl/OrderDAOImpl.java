package lk.ijse.DAO.impl;

import lk.ijse.DAO.custome.OrderDAO;
import lk.ijse.Entity.Orders;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class  OrderDAOImpl implements OrderDAO {
    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);
        transaction.commit();
        session.close();
        return transaction.getStatus().canRollback();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId="";

        Object order = session.createQuery("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1").uniqueResult();
        if (order!=null) {
            String orderId = order.toString();
            String prefix = "O";
            String[] split = orderId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        }else {
            return "O001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }

    @Override
    public Orders searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


}
