package lk.ijse.BO;


import lk.ijse.BO.custome.CustomerBO;
import lk.ijse.BO.impl.*;


public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM
    }


    public SuperBO getBO(BOTypes type){
        switch(type){
            case CUSTOMER:
                return new CustomerBOImpl();

            case ITEM:
                return new ItemBOImpl();

            default:
                return null;
        }
    }

}
