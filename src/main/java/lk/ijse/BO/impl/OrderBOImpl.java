package lk.ijse.BO.impl;

import lk.ijse.BO.custome.OrderBo;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custome.OrderDAO;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBo {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    @Override
    public String generateNextIdOrder() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
    }
}
