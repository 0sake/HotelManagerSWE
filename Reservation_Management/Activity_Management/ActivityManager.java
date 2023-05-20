package Reservation_Management.Activity_Management;

import java.sql.*;
import java.util.ArrayList;
//import java.lang.Integer;

import Database_Connector.MySqlCon;

public class ActivityManager {
    //se l'attività esiste già ritorna false, altrimenti se non esiste il db (activityname)reservation lo crea e ritorna true
    public Boolean createNewActivity(String activityName, int fieldNumber){
        //TODO alla creazione di una nuova attività devo anche creare il db delle prenotazioni per quella attività
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from activitysetup");
            //non posso avere resultset che ritornano vuoti, quindi devo fare una query per vedere se esiste già l'attività
            //ResultSet rs2 = stmt.executeQuery("select database()");
            Boolean nameMatch = false;
            while(rs.next()){
                if(rs.getString(1).equals(activityName)){
                    //entro con nome matchato
                    nameMatch = true;
                    if(rs.getInt(2) == fieldNumber){
                        //entro con nome e campo matchati
                        System.out.println("Activity already exists");
                        return false;
                    }
                }
            }
            //qua arrivo se trovo match di nome ma non di campo oppure no match
            if(stmt.executeUpdate("insert into activitysetup values ('"+activityName+"',"+fieldNumber+")") != 0){
                System.out.println("Activity succesfully created");
                if(!nameMatch){
                    stmt.executeUpdate("create table "+activityName+"reservation (ActivityDate date, StartTime time, EndTime time, UserEmail varchar(30), fieldNumber smallint)");
                }
                return true;
            }else{
                System.out.println("[ERROR] Activity not created");
                return false;
            }
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
        System.out.println("Lista delle attività: ");
        for(String activityType : activityTypes){
            System.out.println("Nome attività: " + activityType);
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
