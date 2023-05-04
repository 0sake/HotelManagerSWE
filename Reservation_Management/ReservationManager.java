package Reservation_Management;
import User_Management.*;
import java.sql.*;
import java.util.Scanner;

import Database_Connector.*;
import Reservation_Management.Room_Management.Room;

public class ReservationManager {

    public RoomReservation createRoomReservation(int roomNumber, String dateBegin, String dateEnd, User u){
        ReservationFactory rf = new ReservationFactory();
        RoomReservation r = rf.createRoomReservation(roomNumber, dateBegin, dateEnd, u);
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            
            //ResultSet rs = stmt.executeQuery("select * from roomreservation");
            //nuova query che cerca tra le room reservation quelle che matchano l'id della camera chew vogliamo prenotare

            ResultSet rs = stmt.executeQuery("select * from roomreservation where roomID = '"+roomNumber+"'");

            Boolean guard = false;
            while(!guard){
                guard = true;
                while(rs.next()){
                    //preservo il vecchio metodo di controllo della data per problemi eventuali al nuovo metodo
                    // if(rs.getString(1).equals(Integer.toString(roomNumber)) && rs.getString(2).equals(dateBegin) && rs.getString(3).equals(dateEnd)){
                    //     System.out.println("Prenotazione già effettuata");
                    //     guard = false;
                    // }
                    //nuovo metodo di controllo della sovrapposizione dei range delle date della prenotazione da effettuare con quelle già effettuate
                    //TODO controllare che il metodo funzioni
                    if(!(checkDateValidity(dateBegin, dateEnd, rs.getString(2), rs.getString(3)))){
                        System.out.println("Prenotazione già effettuata");
                        guard = false;
                    }
                }
                if(!guard){
                    System.out.println("Inserire una nuova stanza:");
                    roomNumber = Integer.parseInt(new Scanner(System.in).nextLine());
                    System.out.println("Inserire una nuova data di inizio:");
                    dateBegin = new Scanner(System.in).nextLine();
                    System.out.println("Inserire una nuova data di fine:");
                    dateEnd = new Scanner(System.in).nextLine();
                }
            }
            String query = "insert into roomreservation (roomID, startDate, EndDate, UserEmail) values ('"+roomNumber+"','"+dateBegin+"','"+dateEnd+"','"+u.getEmail()+"')";
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }

    public ActivityReservation createActivityReservation(int id, String data, String timeStart, String timeEnd, User u) {
        ReservationFactory rf = new ReservationFactory();
        ActivityReservation a = rf.createActivityreservation(id, data, timeStart, timeEnd, u);
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+ a.getActivityType()+"reservation ");
            Scanner sc = new Scanner(System.in);
            Boolean guard = false;
            while(!guard){
                guard = true;
                while(rs.next()){
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    if(rs.getString(1).equals(data) && rs.getString(2).equals(timeStart+":00")){
                        System.out.println("Prenotazione già effettuata");
                        guard = false;
                    }
                }
                if(!guard){
                    System.out.println("Inserire una nuova data:");
                    data = sc.nextLine();
                    System.out.println("Inserire un nuovo orario di inizio:");
                    timeStart = sc.nextLine();
                    System.out.println("Inserire un nuovo orario di fine:");
                    timeEnd = sc.nextLine();
                    a.setDateBegin(data);
                    a.setTimeStart(timeStart);
                    a.setTimeEnd(timeEnd);
                }
            }
            String query = "insert into "+ a.getActivityType()+"reservation (ActivityDate,StartTime,EndTime,UserEmail) values ('"+a.dateBegin+"','"+a.timeStart+"','"+a.timeEnd+"','"+u.getEmail()+"')";
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }
    //se la prenotazione non viene trovata la funzione restituisce null
    public RoomReservation searchRoomReservation(int roomNumber, String email){
        RoomReservation r = null;
        ReservationFactory rf = new ReservationFactory();
        UserManager um = new UserManager();
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomreservation");
            while(rs.next()){
                if(rs.getString(1).equals(Integer.toString(roomNumber)) && rs.getString(4).equals(email)){
                    r = rf.createRoomReservation(roomNumber, rs.getString(2), rs.getString(3), um.findUserFromEmail(email));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }

    public void cancelRoomReservation(RoomReservation rr){
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            String query = "delete from roomreservation where roomID = '"+rr.getId()+"' and startDate = '"+rr.getDateBegin()+"' and endDate = '"+rr.getDateEnd()+"' and UserEmail = '"+rr.getUser().getEmail()+"'";
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void showAllReservation(){
        
    }

    public void showRoomReservation(RoomReservation r){
        
    }

    public void showActivityReservation(ActivityReservation a){
        
    }

    //ritorna falso se il range [dataBegin, dataEnd] è contenuto nel range [dataDbBegin, dataDbEnd]
    public Boolean checkDateValidity(String dataBegin, String dataEnd, String dataDbBegin, String dataDbEnd){
        
        if(((dataBegin.compareTo(dataDbBegin) > 0) && (dataDbEnd.compareTo(dataBegin) > 0)) || ((dataEnd.compareTo(dataDbBegin) > 0) && (dataDbEnd.compareTo(dataEnd) > 0))){
            return false;
        } 
        
        return true;
    }
  

   
    
}
