package user_interface;
import Reservation_Management.*;
import Reservation_Management.Room_Management.Room;
import Reservation_Management.Room_Management.RoomManager;
import User_Management.*;
import Database_Connector.*;
import java.sql.*;
import java.util.*;

public class Menu {

    public void menuSelector(User u){
        if(u.getName().equals("admin")){
            adminMenu(u);
        }else{
            menu(u);
        }
    }

    public void menu(User u){
        boolean guard = true;
        while(guard){
            System.out.println("[MENU PRINCIPALE] 0-CREA NUOVA PRENOTAZIONE 1-ELIMINA PRENOTAZIONE ESISTENTE 2-VISUALIZZA CALENDARIO 3- 4-USCITA");
            Scanner scanner1 = new Scanner(System.in);
            int choice = Integer.parseInt(scanner1.nextLine());
            switch(choice){
                case 0:
                    System.out.println("0-Prenotazine camera 1-Prenotazione attività");
                    choice = Integer.parseInt(scanner1.nextLine());
                    nuovaPrenotazione(choice,u);
                    break;
                case 1:
                    System.out.println("0-Prenotazine camera 1-Prenotazione attività");
                    choice = Integer.parseInt(scanner1.nextLine());
                    eliminaPrenotazione(choice,u);
                    break;
                case 2:
                case 3:
                case 4:
                    guard = false;
                    break;
            }
        }

        
    }

    public void adminMenu(User u){
        boolean guard = true;
        while(guard){
            System.out.println("[MENU ADMIN] 0-UTENTI 2-CAMERE 3-ATTIVITA' 4- 5-USCITA");
            Scanner scanner1 = new Scanner(System.in);
            int choice = Integer.parseInt(scanner1.nextLine());
            UserManager um = new UserManager();
            switch(choice){
                case 0:
                    System.out.println("[MENU ADMIN] 0-AGGIUNGI UTENTE 1-ELIMINA UTENTE");
                    choice = Integer.parseInt(scanner1.nextLine());
                    switch(choice){
                        case 0:
                            um.registerNewUser();
                            break;
                        case 1:
                            System.out.println("Inserire l' email dell'utente da eliminare:");
                            String email = scanner1.nextLine();
                            um.deleteUser(email);
                            break;
                    }
                case 1:
                    System.out.println("[MENU ADMIN] 0-AGGIUNGI CAMERA 1-ELIMINA CAMERA");
                    //TODO aggiungere funzione addRoom e deleteRoom
                    RoomManager rm = new RoomManager();
                    choice = Integer.parseInt(scanner1.nextLine());
                    switch(choice){
                        case 0:
                            rm.addRoom();
                            break;
                        case 1:
                            System.out.println("Inserire l' id della camera da eliminare:");
                            int id = Integer.parseInt(scanner1.nextLine());
                            rm.deleteRoom(id);
                            break;
                    }
                case 2:
                    //TODO aggiungere funzione addActivity 
                    //ActivityManager am = new ActivityManager();
                    //am.addActivity();
                    break;
                case 3:
                    //TODO aggiungere funzione showActivities e deleteActivity
                    //ActivityManager am = new ActivityManager();
                    // am.showActivities();
                    System.out.println("Inserire l' id dell'attività da eliminare:");
                    int id = Integer.parseInt(scanner1.nextLine());
                    //am.deleteActivity(id);
                    break;
                case 4:
                case 5:
                    guard = false;
                    break;
            }
        }
    }



    public User login(){
        ReservationManager rm = new ReservationManager();
        UserManager um = new UserManager();
        User u = new User();
        System.out.println("Benvenuto nel sistema di prenotazione.");
        System.out.println("0-Registra un nuovo utente 1-Accedere come utente esistente");
        Scanner scanner1 = new Scanner(System.in);
        int data = Integer.parseInt(scanner1.nextLine());   
        switch(data){
            case 0:
                u = um.registerNewUser();
                System.out.println("Nuovo utente creato.");
                System.out.println("Nome: " + u.getName() + "   Email: " + u.getEmail() );
                break;
            case 1:
                u = um.loginUser();
                System.out.println("Utente trovato.");
                System.out.println("Nome: " + u.getName() + "   Email: " + u.getEmail() );
                break;
        } 
        return u;
    }
    


    //TODO aggiungere cancellazione attività
    public void eliminaPrenotazione(int tipoPrenotazione, User u){
        ReservationManager rm = new ReservationManager();
        Scanner scanner1 = new Scanner(System.in);
        switch(tipoPrenotazione){
            case 0: 
                //dobbiamo prima mostrare le prenotazioni effettuate dall' utente loggato
                rm.showUserRoomReservations(u);
                System.out.println("Inserire il numero di camera della quale vogliamo eliminare la prenotazione:");
                int roomNumber = Integer.parseInt(scanner1.nextLine());
                try{
                    Connection conn = MySqlCon.initConnessione();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from roomreservation where roomid = '"+roomNumber+"' AND email = '"+u.getEmail()+"'");
                    RoomReservation rr;
                    int counter = 0;
                    ArrayList<RoomReservation> roomReservations = new ArrayList<RoomReservation>();
                    while(rs.next()){
                        roomReservations.add(new RoomReservation(rs.getInt("RoomId"),rs.getString("StartDate"),rs.getString("EndDate"),u));
                        //counter++;
                    }
                    if(roomReservations.size() == 1){
                        rr = roomReservations.get(0);
                        rm.cancelRoomReservation(rr);
                    }else{
                        System.out.println("Inserire la data di inizio della prenotazione da eliminare:");
                        String startDate = scanner1.nextLine();
                        for(RoomReservation r : roomReservations){
                            if(r.getDateBegin().equals(startDate)){
                                rr = r;
                                rm.cancelRoomReservation(rr);
                            }
                        }
                    }
        
                }catch(Exception e){
                    System.out.println(e);
                }
                        
            case 1:
        }

        


    }

    
    public  void nuovaPrenotazione(int tipoPrenotazione, User u){
         
        // UserManager um = new UserManager();
        // User u = new User();
        // System.out.println("0-nuovo utente 1-utente esistente");
         
        // int data = Integer.parseInt(scanner1.nextLine());   
        // switch(data){
        //     case 0:
        //         u = um.registerNewUser();
        //         System.out.println("Nuovo utente creato.");
        //         System.out.println("Nome: " + u.getName() + "   Email: " + u.getEmail() );
        //         break;
        //     case 1:
        //         u = um.login();
        //         System.out.println("Utente trovato.");
        //         System.out.println("Nome: " + u.getName() + "   Email: " + u.getEmail() );
        //         break;
        // } 
        ReservationManager rm = new ReservationManager();
        Scanner scanner1 = new Scanner(System.in);
        int roomNumber,idAttività;
        if(tipoPrenotazione == 0){
            //TODO controllare che la stringa inserita sia una data valida 
            System.out.println("Inserire il numero di persone per le quali vuole prenotare:");
            int nPosti = Integer.parseInt(scanner1.nextLine());
            System.out.println("Inserire la data di inizio visualizzazione disponibilità (YYYY-MM-DD):");
            String dataInizioDispo = scanner1.nextLine();
            Boolean guard = false;
            while(!guard){
                
                rm.checkRoomAvailability(nPosti,dataInizioDispo);
                
                System.out.println("Inserire il numero della stanza che si vuole prenotare:");
                roomNumber = Integer.parseInt(scanner1.nextLine());
                System.out.println("Inserire la data di inizio prenotazione (YYYY-MM-DD):");
                String dateBegin = scanner1.nextLine();
                System.out.println("Inserire la data di fine prenotazione (YYYY-MM-DD):");
                String dateEnd = scanner1.nextLine();
    
                try{
                    Connection conn = MySqlCon.initConnessione();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from roomreservation where roomId = '"+roomNumber+"'");
                    while(rs.next() && !guard){
                        if(!(rm.checkDateValidity(dateBegin, dateEnd, rs.getString(2), rs.getString(3)))){
                            System.out.println("Non ci sono camere disponibili per il periodo selezionato.");
                        }else{
                            RoomReservation rR = rm.createRoomReservation(roomNumber, dateBegin, dateEnd, u);
                            System.out.println("Prenotazione creata con successo Numero stanza:" + rR.getId() + "   Data di inizio:" + rR.getDateBegin() + "   Data di fine: " + rR.getDateEnd() + "   Utente: " + rR.getUser().getName() );
                            guard = true;
                        }
                    }
                }catch(SQLException e){
                    System.out.println("Errore nella creazione dello statement");
                }
            }
        }else{
            //TODO aggiornare la creazione di una prenotazione per attività
            //devo prima mostrare le attività disponibili, far selezionare il tipo di attività, poi mostrare i campi disponibili e far selezionare il campo e poi creare la prenotazione



            // System.out.println("Inserire la data dell' attività (YYYY-MM-DD):");
            // String dataAttività = scanner1.nextLine();
            // System.out.println("Inserire l' orario di inizio dell' attività (hh:mm):");
            // String orarioInzio = scanner1.nextLine();
            // System.out.println("Inserire l' orario di fine (hh:mm):");
            // String orarioFine = scanner1.nextLine();
            // System.out.println("Inserire il tipo di attività dell' attività [0-Tennis]:");
            // idAttività = Integer.parseInt(scanner1.nextLine());
            // ActivityReservation aR = rm.createActivityReservation(idAttività, dataAttività, orarioInzio, orarioFine, u);
            // System.out.println("Prenotazione creata con successo  Attività:" + aR.getActivityType() + "   Data: " + aR.getDateBegin() + "   Ora inizio : " + aR.getTimeStart() + "   Ora fine : " + aR.getTimeEnd() + "   Utente: " + aR.getUser().getName() );
        }
    }

    public void calendario(){}


}
