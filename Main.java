import User_Management.*;
import Reservation_Management.*;
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
        Menu m1 = new Menu();
        //provo ad implementare un menù come una funzione ricorsiva
        m1.menu();

        
        

        


        

    }
    //definizione della funzione test
    public static void test(){
        UserManager um = new UserManager();
        um.registerNewUser();
        um.showUsers();
        um.registerUserAuto("pollo@swe.it","andrea");
        um.showUsers();
        um.registerUserAuto("rollo@swe.it","francesco");
        um.showUsers();
        um.deleteUser(um.getUserList().get(1));
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

    }


    
    
    

}