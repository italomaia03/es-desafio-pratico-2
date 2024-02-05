package app.repository.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IDAO<T> {

  T get(Long id) throws SQLException, ParseException;
  List<T> getAll() throws SQLException, ParseException;
  boolean save(T t) throws SQLException;
  boolean update(Long id, T t) throws SQLException;
  boolean delete(Long id) throws SQLException;
}
