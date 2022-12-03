package Reservation_Management;
import User_Management.*;

public class ReservationFactory {
    
    public RoomReservation createRoomReservation(int date,int duration,User u){
        RoomReservation r = new RoomReservation(date, duration, u);
        return  r;
    }

    public ActivityReservation createActivityreservation(int date,int duration,User u){
        ActivityReservation a = new ActivityReservation(date,duration,u);
        return  a;
    }
}
 