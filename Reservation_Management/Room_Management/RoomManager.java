package Reservation_Management.Room_Management;
import java.util.*;
public class RoomManager {
    protected ArrayList<Room> roomList = new ArrayList<>();

    public void createRoom(int roomNumber){
        RoomFactory rf = new RoomFactory();
        roomList.add(rf.createRoom(roomNumber));
    }


    public void removeRoom(Room r0){
        for(int i=0;i<roomList.size();i++){
            if(r0 == roomList.get(i)){
                roomList.remove(i);
            }
        }
    }

    public ArrayList<Room> getRoomList(){
        return roomList;
    }


}
