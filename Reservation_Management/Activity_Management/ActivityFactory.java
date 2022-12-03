package Reservation_Management.Activity_Management;

public class ActivityFactory {
    public Activity createActivity(String inputActivityName){
        Activity activity = new Activity(inputActivityName);
        return activity;
    }
}
