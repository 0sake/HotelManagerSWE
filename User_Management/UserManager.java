package User_Management;

import java.util.*;

public class UserManager {
    protected ArrayList<User> userList = new ArrayList<User>();

    public void registerUser(){
        Scanner sc = new Scanner(System.in);
        UserFactory uf = new UserFactory();
        System.out.print("Name:");
        String name = sc.nextLine();
        System.out.print("Email:");
        String email = sc.nextLine();
        userList.add(uf.createUser(name, email));
        //sc.close(); utile ma non necessario
    
    }

    public void registerUserAuto(String email,String name){
        UserFactory uf = new UserFactory();
        userList.add(uf.createUser(name, email));
    }

    public void showUsers(){
        if(userList.size() == 0){
            System.out.println("None");
        }
        for(User i : userList){
            System.out.println(i.name);
            System.out.println(i.email);
        }
    }

    public void deleteUser(User u0){
        for(int i=0;i<userList.size();i++){
            if(u0 == userList.get(i)){
                userList.remove(i);
            }
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void deleteAllUsers(){
        userList.clear();
    }
}
