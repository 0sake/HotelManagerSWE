package Database_Connector;
import java.sql.*;

public class MySqlCon {
    
    public void testDriver(){
        System.out.println("Connessione al database...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver caricato");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trovato");
            e.printStackTrace();
        }
    }

    public static Connection initConnessione(){
        String url = "jdbc:mysql://localhost:3306/hotel";
        String username = "root";
        String password = "root";
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void testQuery(){
        Connection conn = initConnessione();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }   
}
