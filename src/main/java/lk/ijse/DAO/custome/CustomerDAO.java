package lk.ijse.DAO.custome;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<String> getCustomerId();
}
