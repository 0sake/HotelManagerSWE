package Reservation_Management;
import User_Management.*;

public class ActivityReservation extends Reservation {
    
    public ActivityReservation(int date,int duration,User u){
        this.dateBegin = date;
        this.durationTime = duration;
        this.user = u;
    }
    
}
