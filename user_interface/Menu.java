package user_interface;
import Reservation_Management.*;
import Reservation_Management.Activity_Management.Activity;
import Reservation_Management.Activity_Management.ActivityFactory;
import Reservation_Management.Activity_Management.ActivityManager;
import Reservation_Management.Room_Management.Room;
import Reservation_Management.Room_Management.RoomFactory;
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
            System.out.println("[MENU PRINCIPALE] 0-CREA NUOVA PRENOTAZIONE 1-ELIMINA PRENOTAZIONE ESISTENTE 2-VISUALIZZA CALENDARIO 3-USCITA");
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
                    guard = false;
                    break;
            }
        }

        
    }

    public void adminMenu(User u){
        boolean guard = true;
        while(guard){
            System.out.println("[MENU ADMIN] 0-UTENTI 1-CAMERE 2-ATTIVITA' 4- 5-USCITA");
            Scanner scanner1 = new Scanner(System.in);
            int choice = Integer.parseInt(scanner1.nextLine());
            UserManager um = new UserManager();
            Boolean error = true;
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
                    RoomManager rm = new RoomManager();
                    int roomNumber,places;
                    choice = Integer.parseInt(scanner1.nextLine());
                    switch(choice){
                        case 0:
                            System.out.println("Inserire il numero della camera che si vuole aggiungere:");
                            roomNumber = Integer.parseInt(scanner1.nextLine());
                            System.out.println("Inserire il numero di posti letto della camera che si vuole aggiungere:");
                            places = Integer.parseInt(scanner1.nextLine());
                            while(error){
                                if(rm.createRoom(roomNumber,places) == false){
                                    System.out.println("[ERRORE]La camera è già presente nel database.");
                                    error = true;
                                }else{
                                    System.out.println("La camera è stata aggiunta correttamente.");
                                    error = false;
                                }
                            }
                            break;
                        case 1:
                            while(error){
                                ArrayList<Room> rooms = rm.getRoomList();
                                for(Room r : rooms){
                                    System.out.println(" Numero: " + r.getRoomNumber() + " Posti letto: " + r.getPlaces());
                                }
                                System.out.println("Inserire il numero della camera da eliminare:");
                                roomNumber = Integer.parseInt(scanner1.nextLine());
                                if(rm.removeRoom(roomNumber) == false){
                                    System.out.println("[ERRORE]La camera non è stata eliminata correttamente.");
                                    error = true;
                                }else{
                                    System.out.println("La camera è stata eliminata correttamente.");
                                    error = false;
                                } 
                            } 
                           
                            break;
                    }
                case 2:
                    System.out.println("[MENU ADMIN] 0-AGGIUNGI CAMERA 1-ELIMINA CAMERA");
                    choice = Integer.parseInt(scanner1.nextLine());
                    switch(choice){
                        case 0:
                            break;
                        case 1:
                            break;
                    }
                    //TODO aggiungere funzione addActivity 
                    //ActivityManager am = new ActivityManager();
                    //am.addActivity();
                    //TODO aggiungere funzione showActivities e deleteActivity
                    //ActivityManager am = new ActivityManager();
                    // am.showActivities();
                    System.out.println("Inserire l' id dell'attività da eliminare:");
                    int id = Integer.parseInt(scanner1.nextLine());
                    //am.deleteActivity(id);
                    break;
                case 3:
                    break;
                case 4:
                case 5:
                    guard = false;
                    break;
            }
        }
    }



    public User loginMenu(){
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
    



    public void eliminaPrenotazione(int tipoPrenotazione, User u){
        ReservationManager rm = new ReservationManager();
        Scanner scanner1 = new Scanner(System.in);
        switch(tipoPrenotazione){
            case 0: 
                //dobbiamo prima mostrare le prenotazioni effettuate dall' utente loggato
                Boolean error = true;
                while(error){
                    rm.showUserRoomReservations(u);
                    System.out.println("Inserire il numero di camera della quale vogliamo eliminare la prenotazione:");
                    int roomNumber = Integer.parseInt(scanner1.nextLine());
                    try{
                        Connection conn = MySqlCon.initConnessione();
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from roomreservation where roomid = '"+roomNumber+"' AND email = '"+u.getEmail()+"'");
                        RoomReservation rr;
                        ArrayList<RoomReservation> roomReservations = new ArrayList<RoomReservation>();
                        while(rs.next()){
                            roomReservations.add(new RoomReservation(rs.getInt("RoomId"),rs.getString("StartDate"),rs.getString("EndDate"),u));
                        }
                        if(roomReservations.size() == 1){
                            rr = roomReservations.get(0);
                            if(rm.cancelRoomReservation(rr) == false){
                                System.out.println("Errore nell' eliminazione della prenotazione.");
                                error = true;
                            }else{
                                System.out.println("Prenotazione eliminata con successo.");
                                error = false;
                            }
                        }else{
                            System.out.println("Inserire la data di inizio della prenotazione da eliminare:");
                            String startDate = scanner1.nextLine();
                            for(RoomReservation r : roomReservations){
                                if(r.getDateBegin().equals(startDate)){
                                    rr = r;
                                    if(rm.cancelRoomReservation(rr) == false){
                                        System.out.println("Errore nell' eliminazione della prenotazione.");
                                        error = true;
                                    }else{
                                        System.out.println("Prenotazione eliminata con successo.");
                                        error = false;
                                    }
                                }
                            }
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
                        
            case 1:
                //TODO aggiungere cancellazione attività
                rm.showUserActivityReservation(u);
                System.out.println("Inserire l' attività da eliminare:");
                String activityName = scanner1.nextLine();
                System.out.println("Inserire il campo della prenotazione da eliminare:");
                int fieldNumber = Integer.parseInt(scanner1.nextLine());
                System.out.println("Inserire la data di inizio della prenotazione da eliminare:");
                String startDate = scanner1.nextLine();
                ReservationFactory rf = new ReservationFactory();
                //TODO una volta implementato il costruttore in ReservationFactory chiamiamo cancelActivityReservation e con gli if controlliamo se è andato a buon fine



                break;
        }

        


    }

    
    public  void nuovaPrenotazione(int tipoPrenotazione, User u){
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
                            System.out.println("Prenotazione creata con successo Numero stanza:" + rR.getRoomNumber() + "   Data di inizio:" + rR.getDateBegin() + "   Data di fine: " + rR.getDateEnd() + "   Utente: " + rR.getUser().getName() );
                            guard = true;
                        }
                    }
                }catch(SQLException e){
                    System.out.println("Errore nella creazione dello statement");
                }
            }
        }else{
            ActivityManager am = new ActivityManager();
            am.showActivitiesTypes();
            System.out.println("Inserire il tipo di attività che si vuole prenotare:");
            String activityType = scanner1.nextLine();
            Boolean match = false;
            while(!match){
                for(Activity a : am.getActivityList()){
                    if(a.getActivityName().equals(activityType)){
                        match = true;
                    }
                }
                if(!match){
                    System.out.println("Attività non trovata.");
                    System.out.println("Inserire il tipo di attività che si vuole prenotare:");
                    activityType = scanner1.nextLine();
                }
            }

            System.out.println("Inserire la data di inizio visualizzazione disponibilità (YYYY-MM-DD):");
            String dataInizioDispo = scanner1.nextLine();
            rm.checkActivityAvailability(activityType, dataInizioDispo);


            System.out.println("Inserire il campo che si vuole prenotare:");
            int fieldNumber = Integer.parseInt(scanner1.nextLine());
            System.out.println("Inserire la data dell' attività (YYYY-MM-DD):");
            String dataAttività = scanner1.nextLine();
            System.out.println("Inserire l' orario di inizio dell' attività (hh:mm):");
            String orarioInzio = scanner1.nextLine();
            System.out.println("Inserire l' orario di fine (hh:mm):");
            String orarioFine = scanner1.nextLine();

            ActivityReservation aR = rm.createActivityReservation(activityType, fieldNumber, dataAttività, orarioInzio, orarioFine, u);
            System.out.println("Prenotazione creata con successo  Attività:" + aR.getActivityType() + "   Data: " + aR.getDateBegin() + "   Ora inizio : " + aR.getTimeStart() + "   Ora fine : " + aR.getTimeEnd() + "   Utente: " + aR.getUser().getName() );
        }
    }

    public void calendario(){}


}
