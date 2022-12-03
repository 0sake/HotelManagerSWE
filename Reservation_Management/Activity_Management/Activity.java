package Reservation_Management.Activity_Management;

public class Activity {
    protected String activityName;

    public String getActivityName(){
        return this.activityName;
    }

    public Activity(String inputActivityName){
        this.activityName = inputActivityName;
    }
}
