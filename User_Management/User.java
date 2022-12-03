package User_Management;

public class User {
    protected String name = "name";
    protected String email;

    public String getName(){
        System.out.println(this.name);
        return this.name;
    }

    public String getEmail(){
        System.out.println(this.email);
        return this.email;
    }

    public User(String inputName ,String inputEmail ){
        this.name = inputName;
        this.email = inputEmail;
    }
}
