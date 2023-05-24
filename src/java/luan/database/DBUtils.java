package luan.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils implements Serializable{
    public static Connection makeConnection(){
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Luan_SP2023;instanceName=LAPTOP-JLN8M461\\SQLEXPRESS";
            Connection con = DriverManager.getConnection(url,"sa","1234567890");
            return con;
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }
}


