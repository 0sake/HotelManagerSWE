public class Main {
    public static void main(String[] args){
        UserFactory c0 = new UserFactory();
        User u0 = c0.createUser("pollo", "cose");
        User u1 = new User("giacomo","rollo@gmail.com");
        u0.getName();
        u1.getEmail();
    }
}