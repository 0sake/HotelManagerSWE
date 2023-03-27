package Reservation_Management;
import User_Management.*;

public class RoomReservation extends Reservation {
    public RoomReservation(int roomNumber,String dateBegin,String dateEnd,User u){
        this.id = roomNumber;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.user = u;
    }
}
