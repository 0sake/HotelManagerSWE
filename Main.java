import User_Management.*;
import Reservation_Management.*;

import java.sql.*;
import java.util.*;


import user_interface.*;
import Database_Connector.*;

public class Main {
    public static void main(String[] args){

        //test della connessione al database mySQL
        MySqlCon t1 = new MySqlCon();
        t1.testDriver();
        t1.initConnessione();
        t1.testQuery();

        //funzione per testare il fuznionamento del programma
        //test();
        //TODO implementare la creazione del database se non esiste
        Boolean db = false;
        if(db){
            try{
                Connection conn = MySqlCon.initConnessione();
                Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery("show tables");
                Boolean match = false;
                String dbName = "tennis";
                while(rs2.next()){
                    System.out.println("Nome:"+rs2.getString(1));
                    if(rs2.getString(1).equals(dbName+"reservation")){
                        match = true;
                    }
    
                }
                if(match == false){
                    if(stmt.executeUpdate("create database if not exists "+dbName+"reservation") != 0){
                        System.out.println("Database creato");
                    }
                    else{
                        System.out.println("Database già esistente");
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

 


        //provo ad implementare un menù come una funzione ricorsiva

        Menu m1 = new Menu();
        Boolean exit = false;
        while(!exit){
            User u = m1.loginMenu();
            //menu selector apre il menu admin se il nome dell' utente che si registra è admin
            m1.menuSelector(u);
            System.out.println("Vuoi effettuare un nuovo login o vuoi chiudere il programma? 0-Login 1-Chiudi");
            Scanner sc = new Scanner(System.in);
            int scelta = Integer.parseInt(sc.nextLine());
            if(scelta == 1){
                exit = true;
            }

        }


        
        

        


        

    }
    //definizione della funzione test
    public static void test(){
        UserManager um = new UserManager();
        um.registerNewUser();
        um.showUsers();
        //um.registerUserAuto("pollo@swe.it","andrea");
        um.showUsers();
        //um.registerUserAuto("rollo@swe.it","francesco");
        um.showUsers();
        //um.deleteUser(um.getUserList().get(1));
        um.showUsers();
        

        ReservationManager rm =  new ReservationManager();
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Selezionare il tipo di prenotazione 0-Stanza 1-Attività:");
        int scelta = Integer.parseInt(sc1.nextLine());
        //rm.createReservation(scelta, 20221204, 1,um.getUserList().get(0) );
        

        //rm.showRoomReservation(rm.getRoomReservationList().get(0));
        rm.showAllReservation();

        System.out.println("Prenotazione 0-Stanza 1-Attività:");
        scelta = Integer.parseInt(sc1.nextLine());
        
        System.out.println("Data attività:");
        int data = Integer.parseInt(sc1.nextLine());

        //rm.createReservation(scelta, data, 1,um.getUserList().get(1) );

        rm.showAllReservation();
        //rm.showRoomReservation(rm.getRoomReservationList().get(0));

        //rm.createRoomReservationList();

                //modo veloce di confronto fra date
                String data1 = "2023-04-25";
                String data2 = "2023-03-27";
        
                if(data1.compareTo(data2) > 0){
                    System.out.println("La data di inizio deve essere precedente a quella di fine");
                }
        

        //debug chechDateValidity() testata per tutti e 5 i casi di sovrapposizione
        //ora dovrebbe essere totalmente funzionante
        String data1Begin = "2023-04-28";
        String data1End = "2023-04-30";
        String data2Begin = "2023-04-25";
        String data2End = "2023-04-27";
        ReservationManager rrm = new ReservationManager();
        if(rrm.checkDateValidity(data1Begin, data1End, data2Begin, data2End)){
            System.out.println("i range non sono sovrapposti l'uno all'altro");
        }
        else{
            System.out.println("Il range data 1 è contenuto in quello di data 2");
        }

        //lunghezza stringhe per controllo formattazione orario inserito
        String ora01 = "10:00";
        String ora02 = "11";
        System.out.println(ora01.length());
        System.out.println(ora02.length());


        String ora1 = "12:00";
        String ora2 = "13:00";
        String ora3 = "10:00";
        String ora4 = "11:00";
        ReservationManager rm1 = new ReservationManager();
        if(rm1.checkHourOverlap(ora1, ora2, ora3, ora4)){
            System.out.println("Overlap");
        }else{
            System.out.println("No overlap");
        }

    }


    
    
    

}