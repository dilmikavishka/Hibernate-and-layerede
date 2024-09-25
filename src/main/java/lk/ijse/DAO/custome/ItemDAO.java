package lk.ijse.DAO.custome;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    List<String> getItemCode();
}
