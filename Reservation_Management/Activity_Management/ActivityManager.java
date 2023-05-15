package Reservation_Management.Activity_Management;

import java.sql.*;
import java.util.ArrayList;
//import java.lang.Integer;

import Database_Connector.MySqlCon;

public class ActivityManager {
    public Boolean createNewActivity(String activityName, int fieldNumber){
        //TODO alla creazione di una nuova attività devo anche creare il db delle prenotazioni per quella attività
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("selct * from activitysetup where activityName = '" + activityName + "'");
            while(rs.next()){
                if(rs.getString(1).equals(activityName) && rs.getInt(2) == fieldNumber){
                    System.out.println("Activity already exists");
                    return false;
                }
            }
            stmt.executeUpdate("insert into activitysetup values ('"+activityName+"',"+fieldNumber+")");
            System.out.println("Activity created");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean removeActivity(Activity activity0){
        Connection conn = MySqlCon.initConnessione();
        try{
            Statement stmt = conn.createStatement();
            if(stmt.executeUpdate("delete from activitysetup where activityType = '"+activity0.getActivityName()+"' AND fieldId = '" +activity0.getFieldNumber()+"' ") == 0){
                System.out.println("[ERROR] Activity not removed");
                return false;
            }else{
                System.out.println("Activity removed");
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<Activity> getActivityList() {
        ArrayList<Activity> activityList = new ArrayList<Activity>();
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from activitysetup");
            ActivityFactory af = new ActivityFactory();
            while(rs.next()){
                Activity activity = af.createActivity(rs.getString(1), rs.getInt(2));
                activityList.add(activity);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return activityList;
    }


    public void showActivities(){
        ArrayList<Activity> activityList = getActivityList();
        System.out.println("Lista delle attività:");
        for(Activity activity : activityList){
            System.out.println("Nome:" + activity.getActivityName() + " Campo:" + activity.getFieldNumber());
        }
    }

    public ArrayList<String> getActivitiesTypes(){
        ArrayList<Activity> activityList = getActivityList();
        ArrayList<String> activityTypes = new ArrayList<String>();
        for(Activity activity : activityList){
            if(!activityTypes.contains(activity.getActivityName())){
                activityTypes.add(activity.getActivityName());
            }
        }
        return activityTypes;
    }

    public void showActivitiesTypes(){
        ArrayList<String> activityTypes = getActivitiesTypes();
        System.out.println("Lista delle attività:");
        for(String activityType : activityTypes){
            System.out.println("Nome attività:" + activityType);
        }
    }

    //ritorna una lista di interi che rappresentano i campi di una certa attività
    public ArrayList<Integer> getActivitiesFields(String activityName){
        ArrayList<Activity> activityList = getActivityList();
        ArrayList<Integer> activityFields = new ArrayList<Integer>();
        for(Activity activity : activityList){
            if(activity.getActivityName().equals(activityName)){
                activityFields.add(activity.getFieldNumber());
            }
        }
        return activityFields;
    }

    public void showActivityFields(String activityType){
        ArrayList<Integer> activityFields = getActivitiesFields(activityType);
        System.out.println("Lista dei campi dell'attività " + activityType + ":");
        for(Integer activityField : activityFields){
            System.out.println("Campo:" + activityField);
        }
    }
}
