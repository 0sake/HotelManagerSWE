public class User {
    protected String name = "name";
    protected String email;

    public void getName(){
        System.out.println(this.name);
    }

    public void getEmail(){
        System.out.println(this.email);
    }

    public User(String inputName ,String inputEmail ){
        this.name = inputName;
        this.email = inputEmail;
    }
}
