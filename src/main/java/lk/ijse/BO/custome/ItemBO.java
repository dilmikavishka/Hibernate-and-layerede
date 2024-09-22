package lk.ijse.BO.custome;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    String generateNextIdItem() throws SQLException, ClassNotFoundException;
}
