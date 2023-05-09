package User_Management;

public class User {
    protected String name = "name";
    protected String email = "email";

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setName(String inputName){
        this.name = inputName;
    }

    public void setEmail(String inputEmail){
        this.email = inputEmail;
    }

    public User(String inputName ,String inputEmail){
        this.name = inputName;
        this.email = inputEmail;
    }

    public User(){
        this.name = "name";
        this.email = "email";
    }
}
