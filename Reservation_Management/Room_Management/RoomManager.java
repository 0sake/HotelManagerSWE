package Reservation_Management.Room_Management;
import java.util.*;
import Database_Connector.*;
import java.sql.*;
public class RoomManager {

    public Boolean createRoom(int roomNumber, int places){
        //connettere  a roomsetup, controllare che la stanza non sia già presente, se non è presente inserirla
        Connection conn = MySqlCon.initConnessione();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomsetup");
            while(rs.next()){
                if(rs.getInt(1) == roomNumber){
                    System.out.println("Room already exists");
                    return false;
                }
            }
            stmt.executeUpdate("insert into roomsetup values ("+roomNumber+","+places+")");
            System.out.println("Room created");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        };
        return false;
    }


    public Boolean removeRoom(int roomNumber){
        Connection conn = MySqlCon.initConnessione();
        try{
            Statement stmt = conn.createStatement();
            if(stmt.executeUpdate("delete from roomsetup where roomNumber = "+roomNumber) == 0){
                System.out.println("[ERROR] Room not removed");
                return false;
            }
            System.out.println("Room removed");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        };
        return false;
    }

    public ArrayList<Room> getRoomList() {
        ArrayList<Room> roomList = new ArrayList<Room>();
        try{
            Connection conn = MySqlCon.initConnessione();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomsetup");
            RoomFactory rf = new RoomFactory();
            while(rs.next()){
                Room room = rf.createRoom(rs.getInt(1), rs.getInt(2));
                roomList.add(room);
            }
        }catch(SQLException e){
            e.printStackTrace();
        };
        return roomList;
    }



}
