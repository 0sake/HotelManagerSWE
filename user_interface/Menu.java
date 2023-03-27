package user_interface;
import Reservation_Management.*;
import User_Management.*;
import Database_Connector.*;
import java.util.*;

public class Menu {
    public void menu(){
        boolean guard = true;
        while(guard){
                System.out.println("[MENU PRINCIPALE] 0-CREA NUOVA PRENOTAZIONE 1-ELIMINA PRENOTAZIONE ESISTENTE 2-VISUALIZZA CALENDARIO 3- 4-USCITA");
                Scanner scanner1 = new Scanner(System.in);
                int choice = Integer.parseInt(scanner1.nextLine());
                switch(choice){
                    case 0:
                        System.out.println("0-Prenotazine camera 1-Prenotazione attività");
                        choice = Integer.parseInt(scanner1.nextLine());
                        nuovaPrenotazione(choice);
                        break;
                    case 1:
                        System.out.println("0-Prenotazine camera 1-Prenotazione attività");
                        choice = Integer.parseInt(scanner1.nextLine());
                        eliminaPrenotazione(choice);
                        break;
                    case 2:
                    case 3:
                    case 4:
                        guard = false;
                        break;
                }
        }
    }


    //TODO scrivere una funzione che elimina una prenotazione
    public void eliminaPrenotazione(int tipoPrenotazione){


    }


    public  void nuovaPrenotazione(int tipoPrenotazione){
        ReservationManager rm = new ReservationManager();
        UserManager um = new UserManager();
        User u = new User();
        System.out.println("0-nuovo utente 1-utente esistente");
        Scanner scanner1 = new Scanner(System.in);
        int data = Integer.parseInt(scanner1.nextLine());   
        switch(data){
            case 0:
                u = um.registerNewUser();
                System.out.println("Nuovo utente creato.");
                System.out.println("Nome: " + u.getName() + "   Email: " + u.getEmail() );
                break;
            case 1:
                u = um.login();
                System.out.println("Utente trovato.");
                System.out.println("Nome: " + u.getName() + "   Email: " + u.getEmail() );
                break;
        } 
        
        
        int roomNumber,id;
        if(tipoPrenotazione == 0){
            System.out.println("Inserire la data di inizio prenotazione (YYYY-MM-DD):");
            String dataInizio = scanner1.nextLine();
            System.out.println("Inserire la data di fine prenotazione (YYYY-MM-DD):");
            String dataFine = scanner1.nextLine();
            System.out.println("Inserire il numero di camera:");
            roomNumber = Integer.parseInt(scanner1.nextLine());
            RoomReservation rR = rm.createRoomReservation(roomNumber, dataInizio, dataFine, u);
            System.out.println("Prenotazione creata con successo Numero stanza:" + rR.getId() + "   Data di inizio:" + rR.getDateBegin() + "   Data di fine: " + rR.getDateEnd() + "   Utente: " + rR.getUser().getName() );
        }else{
            System.out.println("Inserire la data dell' attività (YYYY-MM-DD):");
            String dataAttività = scanner1.nextLine();
            System.out.println("Inserire l' orario di inizio dell' attività (hh:mm):");
            String orarioInzio = scanner1.nextLine();
            System.out.println("Inserire l' orario di fine (hh:mm):");
            String orarioFine = scanner1.nextLine();
            System.out.println("Inserire il tipo di attività dell' attività [0-Tennis]:");
            id = Integer.parseInt(scanner1.nextLine());
            ActivityReservation aR = rm.createActivityReservation(id, dataAttività, orarioInzio, orarioFine, u);
            System.out.println("Prenotazione creata con successo  Attività:" + aR.getActivityType() + "   Data: " + aR.getDateBegin() + "   Ora inizio : " + aR.getTimeStart() + "   Ora fine : " + aR.getTimeEnd() + "   Utente: " + aR.getUser().getName() );
        }
        
        

    }
}
