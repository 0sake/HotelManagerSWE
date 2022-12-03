package Reservation_Management.Room_Management;

public class RoomFactory {
    public Room createRoom(int i){
        Room r = new Room();
        r.roomName = i;
        return r;
    }
}
