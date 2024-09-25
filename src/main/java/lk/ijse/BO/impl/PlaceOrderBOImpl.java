package lk.ijse.BO.impl;

import lk.ijse.BO.custome.PlaceOderBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custome.CustomerDAO;
import lk.ijse.DAO.custome.ItemDAO;
import lk.ijse.Entity.Customer;

import java.util.List;

public class PlaceOrderBOImpl implements PlaceOderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public List<String> getCustomerId() {
        return customerDAO.getCustomerId();
    }

    @Override
    public List<String> getItemCode() {
        return itemDAO.getItemCode();
    }
}
