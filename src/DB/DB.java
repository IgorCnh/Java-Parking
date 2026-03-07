package DB;

import Exceptions.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static Connection getConnection(){
        Connection conn = null;
        if(conn == null){
            try {
                Properties props = new Properties();
                FileInputStream fs;
                props.load(fs = new FileInputStream("src/db.properties"));
                conn = DriverManager.getConnection(props.getProperty("dburl"), "root", props.getProperty("password"));
            } catch (SQLException e) {
                throw new DBException("Error with SQL: " + e.getMessage());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}