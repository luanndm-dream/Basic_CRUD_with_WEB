package luan.registration;

import luan.database.DBUtils;
import luan.registration.RegistrationDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password)
            throws  SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Select * from Registration Where username = ? And password = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }
    
    private  List<RegistrationDTO> listAccount;
    
    public List<RegistrationDTO> getListAccount(){
        return listAccount;
    }
    
    public void searchLastname(String searchValue) 
        throws SQLException, NamingException{
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Select * from Registration Where lastname Like ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1,"%" + searchValue + "%");

                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    
                    if(this.listAccount == null){
                        this.listAccount = new ArrayList<RegistrationDTO>();
                    }
                    
                    this.listAccount.add(dto);
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
    }
    
    public boolean deleteRecord(String username) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Delete from Registration Where username = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean update(String username, String password, String lastname, boolean role) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Update Registration set password = ?, lastname = ?, isAdmin = ? where username = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, lastname);
                stm.setBoolean(3, role);
                stm.setString(4, username);

                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
        return false;
    }

}
