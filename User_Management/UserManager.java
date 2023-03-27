package User_Management;
import Database_Connector.*;
import java.sql.*;


import java.util.*;

public class UserManager {
    protected ArrayList<User> userList = new ArrayList<User>();


    public User registerNewUser(){
        Scanner sc = new Scanner(System.in);
        Connection conn = Database_Connector.MySqlCon.initConnessione();
        UserFactory uf = new UserFactory();
        System.out.print("Name:");
        String name = sc.nextLine();
        System.out.print("Email:");
        String email = sc.nextLine();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            Boolean guard = false;
            while(!guard){
                guard = true;
                while(rs.next()){
                    if(rs.getString(2).equals(email)){
                        System.out.println("Email già in uso");
                        guard = false;
                    }
                }
                if(!guard){
                    System.out.println("Inserire una nuova email:");
                    email = sc.nextLine();
                }
            }
            if(guard){
                String query = "insert into users (name,email) values ('"+name+"','"+email+"')";
                stmt.executeUpdate(query);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }    
        return uf.createUser(name, email);
        //sc.close(); utile ma non necessario
    
    }

    public User login(){
        Scanner sc = new Scanner(System.in);
        Connection conn = MySqlCon.initConnessione();
        UserFactory uf = new UserFactory();
        System.out.print("Inserire email:");
        String email = sc.nextLine();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            Boolean guard = false;
            while(!guard){
                guard = true;
                while(rs.next()){
                    if(rs.getString(2).equals(email)){
                        return uf.createUser(rs.getString(1), rs.getString(2));
                    }
                }
                System.out.println("Email non trovata");
                guard = false;
                System.out.println("Inserire una nuova email:");
                email = sc.nextLine();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User findUserFromEmail(String email){
        Connection conn = MySqlCon.initConnessione();
        UserFactory uf = new UserFactory();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while(rs.next()){
                if(rs.getString(2).equals(email)){
                    return uf.createUser(rs.getString(1), rs.getString(2));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    //vecchi metodi da aggiornare con il database
    public void registerUserAuto(String email,String name){
        UserFactory uf = new UserFactory();
        userList.add(uf.createUser(name, email));
    }

    public void showUsers(){
        if(userList.size() == 0){
            System.out.println("None");
        }
        for(User i : userList){
            System.out.println(i.name);
            System.out.println(i.email);
        }
    }

    public void showUser(User u0){
        for(int i=0;i<userList.size();i++){
            if(u0 == userList.get(i)){
                System.out.println(userList.get(i).name);
                System.out.println(userList.get(i).email);
            }
        }
    }

    public void deleteUser(User u0){
        for(int i=0;i<userList.size();i++){
            if(u0 == userList.get(i)){
                userList.remove(i);
            }
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void deleteAllUsers(){
        userList.clear();
    }
}
