package Reservation_Management;
import User_Management.*;

public class Reservation {
    
    protected String dateBegin;
    protected String dateEnd;
    protected String timeStart;
    protected String timeEnd;
    protected User user;

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

    public Reservation(){
        this.dateBegin = "";
        this.dateEnd = "";
        this.timeStart = "";
        this.timeEnd = "";
        this.user = null;
    }

    public Reservation(String dateBegin, String dateEnd, String timeStart, String timeEnd, User u){
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.user = u;
    }

    

    //get e set
}
