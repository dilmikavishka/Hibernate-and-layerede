package lk.ijse.BO.impl;

import lk.ijse.BO.custome.ItemBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custome.ItemDAO;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.DTO.ItemDTO;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(item.getItemCode(),item.getItemDesc(),item.getQtyOnHand(),item.getItemPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(item.getItemCode(),item.getItemDesc(),item.getQtyOnHand(),item.getItemPrice()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> items = itemDAO.getAll();
        for (Item item : items){
            itemDTOS.add(new ItemDTO(item.getItemCode(),item.getItemDesc(),item.getQtyOnHand(),item.getItemPrice()));
        }
        return itemDTOS;
    }

    @Override
    public String generateNextIdItem() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNextId();
    }
}
