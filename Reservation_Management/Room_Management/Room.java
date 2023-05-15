package Reservation_Management.Room_Management;


public class Room {
    protected int roomNumber;
    protected int places;

    public int getRoomNumber(Room this){
        return this.roomNumber;
    }

    public int getPlaces(Room this){
        return this.places;
    }

    public void setRoomNumber(int inputRoomNumber){
        this.roomNumber = inputRoomNumber;
    }

    public void setPlaces(int inputPlaces){
        this.places = inputPlaces;
    }

    public Room( int inputRoomNumber, int inputPlaces){
        this.roomNumber = inputRoomNumber;
        this.places = inputPlaces;
    }

    public Room(){
        this.roomNumber = 0;
        this.places = 0;
    }

    public Room(int inputRoomNumber){
        this.roomNumber = inputRoomNumber;
    }

    
}
