public class User {
    protected String name = "name";
    protected String email;

    public void getName(User u){
        System.out.println(u.name);
    }

    public void getEmail(User u){
        System.out.println(u.email);
    }

    public User(String inputName ,String inputEmail ){
        this.name = inputName;
        this.email = inputEmail;
    }
}
