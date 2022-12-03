package Reservation_Management;
import User_Management.*;

public class RoomReservation extends Reservation {
    public RoomReservation(int date,int duration,User u){
        this.dateBegin = date;
        this.durationTime = duration;
        this.user = u;
    }
}
