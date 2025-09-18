package org.example.storage;

import org.example.models.ColumnJDBC;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericRowMapper<T> implements RowMapper<T> {

   private  final Class<T> rowObject;

    public GenericRowMapper() {
        rowObject = null;
    }

    @Override
    public T mapRow(ResultSet resultSet) throws SQLException{
        try{
         T instance = rowObject.getDeclaredConstructor().newInstance();

            Field[] fields = rowObject.getDeclaredFields();

            for (Field field:fields){
                if(field.isAnnotationPresent(ColumnJDBC.class)){
                    ColumnJDBC columnJDBC= field.getAnnotation(ColumnJDBC.class);

                    String columnName= columnJDBC.name();
                }
            }
        }catch (Exception ex){
            throw  new SQLException("Error Mapeando el registro en el objeto", ex);
        }
    }
}
