package Reservation_Management;
import User_Management.*;

public class Reservation {
    protected int id;
    protected String dateBegin;
    protected String dateEnd;
    protected String timeStart;
    protected String timeEnd;
    protected User user;
    
    public int getId(){
        return this.id;
    }

    public String getDateBegin(){
        return this.dateBegin;
    }

    public String getDateEnd(){
        return this.dateEnd;
    }

    public String getTimeStart(){
        return this.timeStart;
    }

    public String getTimeEnd(){
        return this.timeEnd;
    }

    public User getUser(){
        return this.user;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setDateBegin(String dateBegin){
        this.dateBegin = dateBegin;
    }

    public void setDateEnd(String dateEnd){
        this.dateEnd = dateEnd;
    }

    public void setTimeStart(String timeStart){
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd){
        this.timeEnd = timeEnd;
    }   

    public void setUser(User u){
        this.user = u;
    }

    //get e set
}
