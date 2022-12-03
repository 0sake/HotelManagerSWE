package Reservation_Management.Activity_Management;

import java.util.ArrayList;

public class ActivityManager {
    protected ArrayList<Activity> activityList = new ArrayList<>();

    public void createActivity(String activityName){
        ActivityFactory activityf = new ActivityFactory();
        activityList.add(activityf.createActivity(activityName));
    }

    public void removeActivity(Activity activity0){
        for (int i = 0; i <= activityList.size(); i++){
            if (activity0 == activityList.get(i)) {
                activityList.remove(i);
            }
        }
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }
}
