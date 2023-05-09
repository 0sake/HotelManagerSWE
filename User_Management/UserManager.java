package User_Management;
import Database_Connector.*;
import java.sql.*;


import java.util.*;

public class UserManager {


    public User registerNewUser(){
        Scanner sc = new Scanner(System.in);
        Connection conn = Database_Connector.MySqlCon.initConnessione();
        UserFactory uf = new UserFactory();
        System.out.print("Name:");
        String name = sc.nextLine();
        System.out.print("Email:");
        String email = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();
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
                String query = "insert into users (name,email,password) values ('"+name+"','"+email+"','"+password+"')";
                stmt.executeUpdate(query);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }    
        return uf.createUser(name, email);
        //sc.close(); utile ma non necessario
    
    }

    public User loginUser(){
        Scanner sc = new Scanner(System.in);
        Connection conn = MySqlCon.initConnessione();
        UserFactory uf = new UserFactory();
        System.out.print("Inserire email:");
        String email = sc.nextLine();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            Boolean validEmail = false;
            Boolean validPsw = false;
            while(!validEmail && !validPsw){
                while(!validEmail){
                    while(rs.next()){
                        if(rs.getString(2).equals(email)){
                            System.out.println("Email trovata");
                            validEmail = true;
                        }
                    }
                    if(!validEmail){
                        System.out.println("Email non trovata");
                        System.out.println("Inserire una nuova email:");
                        email = sc.nextLine();
                    }
                }
                //email che trovo fuori dal while sarà sicuramente una email valida
                //ora controllo la password
                System.out.println("Inserire password:");
                String password = sc.nextLine();
                //controllo del match tra la password inserita e quella contenua nel database
                while(!validPsw){
                    rs = stmt.executeQuery("select * from users where emaill = '"+email+"'");
                    while(rs.next()){
                        if(rs.getString(3).equals(password)){
                            System.out.println("Password corretta");
                            validPsw = true;
                            return uf.createUser(rs.getString(1), rs.getString(2));
                        }else{
                            System.out.println("Password errata");
                            System.out.println("Inserire una nuova password oppure inserire 0 per cambiare l'email:");
                            password = sc.nextLine();
                            if(password.equals("0")){
                                validEmail = false;
                            }
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    // al momento non utilizzato
    public User findUserFromEmail(String email){
        Connection conn = MySqlCon.initConnessione();
        UserFactory uf = new UserFactory();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while(rs.next()){
                if(rs.getString(2).equals(email)){
                    //TODO modificare perchè in teoria è l'utente loggato a dover essere restituito
                    return uf.createUser(rs.getString(1), rs.getString(2));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    //vecchi metodi da aggiornare con il database


    //funzione che mostra una lista di tutti gli utentiregistrati
    public void showUsers(){
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while(rs.next()){
                System.out.printf("%-15s %-15s%n","Name: "+rs.getString(1)," Email: "+rs.getString(2));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void showUser(User u0){
    }

    public void deleteUser(String email){
        int success = 0;
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            String query = "delete from users where email = '"+email+"'";
            //executeUpdate ritorna 0 se non ha modificato nessuna riga senno ritorna n dove n è il numero di righe modificate
            success = stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(success == 0){
                System.out.println("[ERRORE] Nessun utente eliminato");
            }else{
                System.out.println("Utente eliminato.");
            }
        }
    }



    public void deleteAllUsers(){
    }
}
