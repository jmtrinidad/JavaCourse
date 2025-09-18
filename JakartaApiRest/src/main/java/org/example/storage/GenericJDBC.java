package org.example.storage;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GenericJDBC<T> {

    private final String url;
    private final String user;
    private final String password;

    public GenericJDBC(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    private Connection getConnection() throws SQLException{

        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    public  long countRecords(String tableName) throws SQLException{

        String query ="SELECT COUNT(*) FROM " + tableName;

        Connection connection = this.getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet= statement.executeQuery(query);

        if(resultSet.next()){
            return resultSet.getLong(0);
        }

        return 0L;
    }

    public  T findBy(String tableName, String idColumn, Object idValue, RowMapper<T> rowMapper) throws SQLException {

        String query= "SELECT * FROM " + tableName +" WHERE " +idColumn+ " = ?";
        Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setObject(1, idValue);
        ResultSet resultSet= preparedStatement.executeQuery();

        if(resultSet.next())
            return rowMapper.mapRow(resultSet);

        return null;
    }

    public List<T> findAll(String tableName, RowMapper<T> rowMapper) throws SQLException {

        String query = "SELECT * FROM " + tableName;
         List<T> resultList = new ArrayList<>();

        Connection connection= this.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(query);

        if(resultSet.next()){
            resultList.add(rowMapper.mapRow(resultSet));
        }

        return  resultList;
    }

    public int addRecord(String query, Object... params) throws SQLException {

        Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        this.setParameters(preparedStatement, params);

        int affectedRows = preparedStatement.executeUpdate();

        if(affectedRows > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next())
                return resultSet.getInt(1);
        }

       return  affectedRows;
    }

    public int updateRecord(String query, Object... params) throws SQLException {

        Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        this.setParameters(preparedStatement, params);

      return preparedStatement.executeUpdate();
    }

    public int deleteRecord(String query, Object... params) throws SQLException {

        Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        this.setParameters(preparedStatement, params);

        return preparedStatement.executeUpdate();
    }

    private void setParameters (PreparedStatement preparedStatement, Object... params) throws SQLException{

        for (int i = 0; i < params.length; i++) {
           preparedStatement.setObject(i + 1, params[i]);
        }
    }

}
