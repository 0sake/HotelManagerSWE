package Reservation_Management;
import User_Management.*;
import Database_Connector.*;
import java.sql.*;
public class ReservationFactory {
    //TODO implementare costruttore con solo activityType , fieldNumber, user

    public RoomReservation createRoomReservation(int roomNumber,String dateBegin,String dateEnd,User u){
        RoomReservation r = new RoomReservation(roomNumber,dateBegin, dateEnd, u);      
        return  r;
    }

    public ActivityReservation createActivityreservation(String activityType, int fieldNumber,String date, String timeStart, String timeEnd ,User u){
        ActivityReservation a = new ActivityReservation(activityType, fieldNumber,date,timeStart,timeEnd,u);
        return  a;
    }

    

}
 