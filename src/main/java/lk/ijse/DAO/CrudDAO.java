package lk.ijse.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    public List<T> getAll() throws SQLException, ClassNotFoundException ;
    public  boolean save(T dto) throws SQLException, ClassNotFoundException ;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public  boolean update(T dto) throws SQLException, ClassNotFoundException ;
    String generateNextId() throws SQLException, ClassNotFoundException;




}
