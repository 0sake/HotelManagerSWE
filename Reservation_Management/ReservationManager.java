package Reservation_Management;
import User_Management.*;

import java.util.ArrayList;

public class ReservationManager {
    protected ArrayList<RoomReservation> roomReservationList = new ArrayList<>();
    protected ArrayList<ActivityReservation> activityReservationList = new ArrayList<>();
    public void createReservation(int choice, int date,int duration,User u){
        ReservationFactory rf = new ReservationFactory();
        switch (choice){
            case 0:
                roomReservationList.add(rf.createRoomReservation(date, duration, u));
                break;
            case 1:
                activityReservationList.add(rf.createActivityreservation(date, duration, u));
                break;
            default:
                System.out.println("ERROR 404");
                break;
        }
    }
    
}
