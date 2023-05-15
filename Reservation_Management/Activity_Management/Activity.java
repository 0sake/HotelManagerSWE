package Reservation_Management.Activity_Management;

public class Activity {
    protected String activityName;
    protected int fieldNumber;

    public void setFieldNumber(int inputFieldNumber){
        this.fieldNumber = inputFieldNumber;
    }

    public void setActivityName(String inputActivityName){
        this.activityName = inputActivityName;
    }

    public int getFieldNumber(){
        return this.fieldNumber;
    }

    public String getActivityName(){
        return this.activityName;
    }

    public Activity(){
        this.activityName = "";
        this.fieldNumber = 0;
    }

    public Activity(String inputActivityName, int inputFieldNumber){
        this.activityName = inputActivityName;
        this.fieldNumber = inputFieldNumber;
    }
}
