package Reservation_Management;
import User_Management.*;

import java.util.ArrayList;

public class ReservationManager {
    protected ArrayList<RoomReservation> roomReservationList = new ArrayList<>();
    protected ArrayList<ActivityReservation> activityReservationList = new ArrayList<>();
    public void createReservation(int choice, int date,int duration,User u){
        ReservationFactory rf = new ReservationFactory();
        switch (choice){
            case 0:
                roomReservationList.add(rf.createRoomReservation(date, duration, u));
                break;
            case 1:
                activityReservationList.add(rf.createActivityreservation(date, duration, u));
                break;
            default:
                System.out.println("ERROR 404");
                break;
        }  
    }

    public void showAllReservation(){
        for(RoomReservation i : roomReservationList){
            this.showRoomReservation(i);
        }

        for(ActivityReservation a : activityReservationList){
            this.showActivityReservation(a);
        }
    }

    public void showRoomReservation(RoomReservation r){
        UserManager um0 = new UserManager();

        for(Reservation i : roomReservationList){
            if(i == r){
            System.out.println(String.valueOf(r.getDateBegin()));
            System.out.println(String.valueOf(r.getDurationTime()));
            um0.showUser(r.user);
            }
        }
    }

    public void showActivityReservation(ActivityReservation a){
        UserManager um0 = new UserManager();

        for(Reservation i : activityReservationList){
            if(i == a){
            System.out.println(String.valueOf(a.getDateBegin()));
            System.out.println(String.valueOf(a.getDurationTime()));
            um0.showUser(a.user);
            }
        }
    }

    public ArrayList<RoomReservation> getRoomReservationList(){
        return this.roomReservationList;
    }

    public ArrayList<ActivityReservation> getActivityReservationList(){
        return this.activityReservationList;
    }
    
}
