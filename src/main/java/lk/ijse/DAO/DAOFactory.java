package lk.ijse.DAO;

import lk.ijse.DAO.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        CUSTOMER,ITEM,ORDER
    }

    public SuperDAO getDAO(DAOType daoType){
        switch(daoType){
           case CUSTOMER:
               return new CustomerDAOImpl();
           case ITEM:
               return new ItemDAOImpl();
           case ORDER:
               return new OrderDAOImpl();
            default:
                return null;
        }
    }

}
