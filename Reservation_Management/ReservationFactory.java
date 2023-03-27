package Reservation_Management;
import User_Management.*;
public class ReservationFactory {
    
    public RoomReservation createRoomReservation(int id,String dateBegin,String dateEnd,User u){
        RoomReservation r = new RoomReservation(id,dateBegin, dateEnd, u);
        
        return  r;
    }

    public ActivityReservation createActivityreservation(int id,String date, String timeStart, String timeEnd ,User u){
        ActivityReservation a = new ActivityReservation(id,date,timeStart,timeEnd,u);
        return  a;
    }

    

}
 