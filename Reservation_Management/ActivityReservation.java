package Reservation_Management;
import User_Management.*;

public class ActivityReservation extends Reservation {
    
    protected String activityType;
    protected int fieldNumber;

    public String getActivityType(){
        return this.activityType;
    }

    public int getFieldNumber(){
        return this.fieldNumber;
    }

    public void setActivityType(String activityType){
        this.activityType = activityType;
    }

    public void setFieldNumber(int fieldNumber){
        this.fieldNumber = fieldNumber;
    }

    public ActivityReservation(String activityType,int fieldNumber, String date,String timeStart,String timeEnd,User u){
        this.activityType = activityType;
        this.fieldNumber = fieldNumber;
        this.dateBegin = date;
        this.dateEnd = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.user = u;
    }   
}
