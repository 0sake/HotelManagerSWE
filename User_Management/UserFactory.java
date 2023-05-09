package User_Management;

public class UserFactory {
   
    public User createUser(String inputName ,String inputEmail){ 
        User u = new User(inputName,inputEmail);
        return u;
    }
}
