package Reservation_Management;
import User_Management.*;

public class Reservation {
    protected int dateBegin;
    protected int durationTime;
    protected User user;

    public int getDateBegin(){
        return this.dateBegin;
    }

    public int getDurationTime(){
        return this.durationTime;
    }

    public User getUser(){
        return this.user;
    }

    //get e set
}
