package Reservation_Management.Room_Management;
import Database_Connector.*;
import java.sql.*;

public class RoomFactory {
    public Room createRoom(int roomNumber, int places){
        Room r = new Room();
        r.roomNumber = roomNumber;
        r.places = places;
        return r;
    }

    public Room createRoom(int roomNumber){
        Room r = new Room();
        r.setRoomNumber(roomNumber);
        Connection conn = MySqlCon.initConnessione();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomsetup");
            while(rs.next()){
                if(rs.getInt(1) == roomNumber){
                    r.setPlaces(rs.getInt(2));
                    return r;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        };
        return null;
    }
}
