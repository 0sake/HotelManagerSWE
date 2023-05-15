package User_Management;
import Database_Connector.*;
import java.sql.*;

public class UserFactory {
   
    public User createUser(String inputName ,String inputEmail){ 
        User u = new User(inputName,inputEmail);
        return u;
    }

    public User createUser(String email){
        Connection conn = MySqlCon.initConnessione();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while(rs.next()){
                if(rs.getString(2).equals(email)){
                    User u = new User(rs.getString(1),rs.getString(2));
                    return u;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        };
        return null;
    }
}
