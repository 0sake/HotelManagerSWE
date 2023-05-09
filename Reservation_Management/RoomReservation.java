package Reservation_Management;
import User_Management.*;

public class RoomReservation extends Reservation {
    
    public String getEmail(){
        return this.user.getEmail();
    }

    public String getDateBegin(){
        return this.dateBegin;
    }

    public void setEmail(String inputEmail){
        this.user.setEmail(inputEmail);
    }

    public void setDateBegin(String inputDateBegin){
        this.dateBegin = inputDateBegin;
    }



    public RoomReservation(int roomNumber,String dateBegin,String dateEnd,User u){
        this.id = roomNumber;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.user = u;
    }
}
