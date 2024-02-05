package app.services.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IService<T> {
    String save(T t) throws SQLException, ParseException;
    T get(Long id) throws SQLException, ParseException;
    List<T> getAll() throws SQLException, ParseException;
    String update(Long id, T t) throws SQLException;
    String delete(Long id) throws SQLException;
}
