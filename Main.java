import User_Management.*;
import Reservation_Management.*;
import java.util.*;

public class Main {
    public static void main(String[] args){

        UserManager um = new UserManager();
        um.registerUser();
        um.showUsers();
        um.registerUserAuto("pollo@swe.it","andrea");
        um.showUsers();
        um.registerUserAuto("rollo@swe.it","francesco");
        um.showUsers();
        um.deleteUser(um.getUserList().get(1));
        um.showUsers();
        

        ReservationManager rm =  new ReservationManager();
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Prenotazione 0-Stanza 1-Attività:");
        int scelta = Integer.parseInt(sc1.nextLine());
        rm.createReservation(scelta, 20221204, 1,um.getUserList().get(0) );
        

        rm.showRoomReservation(rm.getRoomReservationList().get(0));
        rm.showAllReservation();

        System.out.println("Prenotazione 0-Stanza 1-Attività:");
        scelta = Integer.parseInt(sc1.nextLine());
        
        System.out.println("Data attività:");
        int data = Integer.parseInt(sc1.nextLine());

        rm.createReservation(scelta, data, 1,um.getUserList().get(1) );

        rm.showAllReservation();
        rm.showRoomReservation(rm.getRoomReservationList().get(0));


        

    }
}