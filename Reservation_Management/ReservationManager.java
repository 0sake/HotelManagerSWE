package Reservation_Management;
import User_Management.*;
import java.sql.*;
import java.util.Scanner;
import java.util.*;

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

    //restituisce vero se le date scelte sono compatibili con qeulle esistenti ed è possibile effettuare la prenotazione
    public void checkRoomAvailability(int nPosti, String dataInizioDispo){

        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomsetup where places = '"+nPosti+"'");
            System.out.println("PRENOTAZIONI GIA' EFFETTUATE:");
            List<Integer> roomList = new ArrayList<Integer>();
            while(rs.next()){
                int roomNumber = rs.getInt("roomId");
                roomList.add(roomNumber);
                int places = rs.getInt("places");
                Connection conn2 = MySqlCon.initConnessione();
                Statement stmt2 = conn2.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select * from roomreservation where roomId = '"+roomNumber+"' AND StartDate >= '"+dataInizioDispo+"'");
                while(rs2.next()){
                    System.out.println("Numero stanza: " + roomNumber + "  " + "Numero di letti: " + places + "  " + "Data inizio: " +  rs2.getString("StartDate") + "  " + "Data fine: " + rs2.getString("EndDate"));
                    //togliendo le camere già prenotate nella lista rimangono solo i numeri delle camere disponibili
                    if(roomList.contains(roomNumber)){
                        roomList.remove(roomList.indexOf(roomNumber));
                    }
                }
            }
            System.out.println("CAMERE NON PRENOTATE:");
            Connection conn3 = MySqlCon.initConnessione();
            Statement stmt3 = conn3.createStatement();
            int i = 0;
            while(i < roomList.size()){
                ResultSet rs3 = stmt3.executeQuery("select * from roomsetup where roomId = '"+roomList.get(i)+"'");
                while(rs3.next()){
                    System.out.println("Numero stanza: " + rs3.getInt("roomId") + "  " + "Numero di letti: " + rs3.getInt("places"));
                }
                i++;
            }

        }catch(SQLException e){
            System.out.println("Errore nella creazione dello statement");
        }

    }


    //se la prenotazione non viene trovata la funzione restituisce null

    //la funzione non viene utilizzata al momento
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

    public void showUserRoomReservations(User u) {
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomreservation where UserEmail = '"+u.getEmail()+"'");
            while(rs.next()){
                System.out.println("RoomID: "+rs.getString(1)+"\nData inizio: "+rs.getString(2)+"\nData fine: "+rs.getString(3)+"\n");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
  

   
    
}
