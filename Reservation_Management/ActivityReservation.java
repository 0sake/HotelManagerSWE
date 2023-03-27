package Reservation_Management;
import User_Management.*;

public class ActivityReservation extends Reservation {
    
    public ActivityReservation(int activityType,String date,String timeStart,String timeEnd,User u){
        this.id = activityType;
        this.dateBegin = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.user = u;
    }

    public String getActivityType(){
        String activityType = "";
        switch(this.id){
            case 0:
                activityType = "Tennis";
                break;
        }
        return activityType;
    }
    
}
