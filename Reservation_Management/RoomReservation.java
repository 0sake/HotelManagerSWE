package Reservation_Management;
import User_Management.*;

public class RoomReservation extends Reservation {
    
    protected int roomNumber;

    public int getRoomNumber(){
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public RoomReservation(int roomNumber,String dateBegin,String dateEnd,User u){
        this.roomNumber = roomNumber;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.user = u;
        //impostiamo l'ora di inizio e fine a valori predefiniti ipotizzando di simulare il tempo di pulizia della stanza
        this.timeStart = "14:00";
        this.timeEnd = "10:00";
    }
}
