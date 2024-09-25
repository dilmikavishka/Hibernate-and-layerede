package lk.ijse.BO.custome;

import lk.ijse.BO.SuperBO;

import java.sql.SQLException;

public interface OrderBo extends SuperBO {
    String generateNextIdOrder() throws SQLException, ClassNotFoundException;
}
