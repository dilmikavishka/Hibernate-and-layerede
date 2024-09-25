package lk.ijse.BO.impl;

import lk.ijse.BO.custome.CustomerBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custome.CustomerDAO;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.Entity.Customer;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers){
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public String generateNextIdCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextId();
    }


    @Override
    public CustomerDTO searchByIdCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(id);
        CustomerDTO result = new CustomerDTO();
        result.setId(customer.getId());
        result.setName(customer.getName());
        result.setAddress(customer.getAddress());
        return  result;
    }

}
