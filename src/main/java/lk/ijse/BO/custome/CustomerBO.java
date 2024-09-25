package lk.ijse.BO.custome;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    String generateNextIdCustomer() throws SQLException, ClassNotFoundException;

    CustomerDTO searchByIdCustomer(String id) throws SQLException, ClassNotFoundException;
}
