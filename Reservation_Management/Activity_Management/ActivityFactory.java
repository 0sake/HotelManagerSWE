package Reservation_Management.Activity_Management;
import Database_Connector.*;
import java.sql.*;

public class ActivityFactory {
    
    public Activity createActivity(String inputActivityName, int inputFieldNumber){
        Activity activity = new Activity(inputActivityName, inputFieldNumber);
        return activity;
    }

    public Activity createActivity(String inputActivityName){
        Activity activity = new Activity();
        activity.setActivityName(inputActivityName);
        Connection conn = MySqlCon.initConnessione();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from activitysetup");
            while(rs.next()){
                if(rs.getString(1).equals(inputActivityName)){
                    activity.setFieldNumber(rs.getInt(2));
                    return activity;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        };
        return null;
    }
}
