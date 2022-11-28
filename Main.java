public class Main {
    public static void main(String[] args){

       
        UserManager um = new UserManager();
        um.registerUser();
        um.showUsers();
        um.registerUserAuto("pollo@swe.it","andrea");
        um.showUsers();
        um.registerUserAuto("rollo@swe.it","francesco");
        um.showUsers();
        um.deleteUser(um.userList.get(1));
        um.showUsers();
        um.deleteAllUsers();
        um.showUsers();
    }
}