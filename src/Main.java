import Classes.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Person activePerson;
    private static Room activeRoom;
    private static List<Room> freeRoomList = new ArrayList<>();

    public static void main(String[] args) {
        Magazine magazine = new Magazine("A1", 5);
        for (int i = 0; i < 10; i++) {
            magazine.addRoom(new Room(Math.round(Math.random() * 950 + 50)));
        }

        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Jan", "Kowalski", "92042203523", "ul. Prosta 1 Warszawa", LocalDate.of(1992, 4, 22)),
                new Person("Adam", "Nowak", "85041554301", "ul. Krzywa 2 Warszawa", LocalDate.of(1985, 4, 15), LocalDate.of(2019, 8, 8)),
                new Person("Krzysztof", "Iksiński", "82062054301", "ul. Polna 3 Warszawa", LocalDate.of(1982, 6, 20)),
                new Person("Piotr", "Piotrowicz", "79012554301", "ul. Wiejska 4 Pcim Dolny", LocalDate.of(1979, 1, 25), LocalDate.of(2019, 5, 10)),
                new Person("Grzegorz", "Brzęczyszczykiewicz", "88101254301", "Koniec świata", LocalDate.of(1988, 10, 12), LocalDate.of(2019, 10, 15))));

        List<Item> itemList = new ArrayList<>(Arrays.asList(
                new Item("Pudło małe", 1),
                new Item("Pudło średnie", 2.5),
                new Item("Pudło duże", 7),
                new Item("Regał", 8, 2, 2),
                new Item("Szafa", 10.84),
                new Item("Akwarium", 2.5)
        ));

        ArrayList<Vehicle> vehicleList = new ArrayList<>(Arrays.asList(
                new Car("Fiat", 15, "Gaz"),
                new Car("Volkswagen", 20, "Diesel"),
                new Car("Nissan", 25.5, "Benzyna"),
                new Motorcycle("Harley Davidson", 4, true),
                new Motorcycle("Kawasaki", 2.9, true),
                new Car("Porsche", 16.78, "Benzyna"),
                new Bike("Składak", 2, 5)
        ));

        // Wyłączenie aktywności dwóch ostatnich pomieszczeń w magazynie
        magazine.getRoomList().get(magazine.getRoomList().size()-1).setActivity(false);
        magazine.getRoomList().get(magazine.getRoomList().size()-2).setActivity(false);

        // Ustawienie Jana Kowalskiego najemcą dwóch pomieszczeń
        magazine.getRoomList().get(0).setTenant(personList.get(0));
        magazine.getRoomList().get(1).setTenant(personList.get(0));

        // Dodanie kilku przedmiotów do pierwszego pomieszczenia
        magazine.getRoomList().get(0).addElement(itemList.get(3));
        magazine.getRoomList().get(0).addElement(itemList.get(1));
        magazine.getRoomList().get(0).addElement(itemList.get(2));
        magazine.getRoomList().get(0).addElement(itemList.get(1));
        magazine.getRoomList().get(0).addElement(itemList.get(5));
        magazine.getRoomList().get(0).addElement(vehicleList.get(0));
        magazine.getRoomList().get(0).addElement(vehicleList.get(4));

        System.out.println("======================= Program do obsługi magazynów =======================\n"+
                           "Menu:\n"+
                           "0. Zakończ program\n"+
                           "1. Wybierz, którą jesteś osobą\n"+
                           "2. Wypisz swoje dane\n"+
                           "3. Wybierz pomieszczenie i następnie wyświetl jego zawartość\n"+
                           "4. Włóż przedmiot lub pojazd do wybranego pomieszczenia\n"+
                           "5. Wybierz przedmiot lub pojazd i wyjmij go z wybranego pomieszczenia\n"+
                           "6. Wyświetl wolne pomieszczenia w magazynie\n"+
                           "7. Wybierz pomieszczenie spośród wolnych i wynajmij je\n"+
                           "8. Zapisz aktualny stan magazynu do pliku\n\n"+
                           "Aby zatwierdzić wybór wybierz odpowiednią cyfrę i kliknij Enter\n"+
                           "============================================================================");

        int choice = -1;
        Scanner in = new Scanner(System.in);

        while(choice != 0){
            System.out.print("\nWybierz opcję z głównego menu: ");
            try {
                choice = in.nextInt();

                switch (choice) {
                    case 0:
                        System.out.println("\nZamykanie programu");
                        break;
                    case 1:
                        for (int i = 0; i < personList.size(); i++) {
                            System.out.println(i + 1 + ". " + personList.get(i));
                        }
                        System.out.print("\nWybierz osobę: ");
                            choice = in.nextInt();
                            if(choice > 0 && choice <= personList.size()) {
                                activePerson = personList.get(choice - 1);
                                System.out.println("Wybrałeś: " + activePerson);
                            }
                            else {
                                System.out.println("Nieprawidłowa wartość");
                            }
                            System.out.println("--------------------------------------------------------------");
                        break;
                    case 2:
                        try {
                            System.out.println(activePerson.getPersonalData());
                            System.out.println("\nPomieszczenia wynajęte przez " + activePerson.toString() + ":");
                            for (int i = 0; i < activePerson.getRoomList().size(); i++) {
                                Room room = activePerson.getRoomList().get(i);
                                System.out.println(i + 1 + ". ID: " + room.getId() + ", Rozmiar w m3: " + room.getMaxCapacity()+", Aktywność: "+room.isActive());
                            }
                        }catch (NullPointerException e) {
                            System.out.println("Nie wybrano żadnej osoby");
                        }
                        System.out.println("--------------------------------------------------------------");
                        break;
                    case 3:
                        try {
                            if(activePerson == null) throw new NullPointerException();
                            System.out.print("Wybierz pomieszczenie: ");
                            choice = in.nextInt();
                            activeRoom = activePerson.getRoomList().get(choice - 1);
                            System.out.print("Wybrano: " + activeRoom+"\n");

                        }catch(NullPointerException e){
                            System.out.println("Nie wybrano żadnej osoby");
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Nieprawidłowy numer pomieszczenia");
                        }
                        System.out.println("--------------------------------------------------------------");
                        break;
                    case 4:
                        try{
                            int i = 0;
                            System.out.println("Przedmioty: ");
                            for (Item item :
                                    itemList) {
                                System.out.println(i+1+". "+itemList.get(i).getName()+" - "+itemList.get(i).getVolume()+" m3");
                                i++;
                            }
                            int temp = itemList.size();
                            System.out.println("Pojazdy: ");
                            for (Vehicle vehicle :
                                    vehicleList) {
                                System.out.println(i+1+". "+vehicleList.get(i-temp).getName()+" - "+vehicleList.get(i-temp).getVolume()+" m3");
                                i++;
                            }

                            System.out.print("Wybierz numer przedmiotu lub pojazdu, który chcesz umieścić w wybranym pomieszczeniu: ");
                            choice = in.nextInt();

                            if(choice <= itemList.size()) {
                                activeRoom.addElement(itemList.get(choice - 1));
                                System.out.println("Dodano: "+itemList.get(choice - 1).getName());
                            }
                            if(choice > itemList.size()) {
                                activeRoom.addElement(vehicleList.get(choice-1-temp));
                                System.out.println("Dodano: "+vehicleList.get(choice-1-temp).getName());
                            }
                            System.out.println("--------------------------------------------------------------");

                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Nieprawidłowy numer przedmiotu lub pojazdu");
                        }catch(NullPointerException e){
                            System.out.println("Nie wybrano pomieszczenia");
                        }
                        break;
                    case 5:
                        try {
                            if(activeRoom == null) throw new NullPointerException();
                            System.out.println("Aktualna zawartość wybranego pomieszczenia:");
                            for (int i = 0; i < activeRoom.getElementList().size(); i++) {
                                System.out.println(i + 1 + ". Nazwa: " + activeRoom.getElementList().get(i).getName() + ", Rozmiar w m3: " + activeRoom.getElementList().get(i).getVolume());
                            }

                            System.out.print("\nWybierz numer elementu do wyjęcia z pomieszczenia: ");
                            choice = in.nextInt();

                            String tempName;
                            tempName = activeRoom.getElementList().get(choice-1).getName();

                            activeRoom.getElementList().remove(choice-1);
                            System.out.println("Element "+tempName+" został wyjęty z pomieszczenia");

                        }catch (NullPointerException e){
                            System.out.println("Nie wybrano pomieszczenia");
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Nieprawidłowa wartość");
                        }
                        System.out.println("--------------------------------------------------------------");
                        break;
                    case 6:
                        System.out.println("Wolne pomieszczenia:");
                        int freeRoomNumber = 0;
                        for (int i = 0; i < magazine.getRoomList().size(); i++) {
                            Room room = magazine.getRoomList().get(i);

                            if(room.getTenant() == null && room.getActivity()) {
                                freeRoomList.add(room);
                                freeRoomNumber++;
                                System.out.println(freeRoomNumber + ". ID: " + room.getId() + ", Rozmiar w m3: " + room.getMaxCapacity()+", Aktywność: "+room.isActive());
                            }
                        }
                        System.out.println("--------------------------------------------------------------");
                        break;
                    case 7:
                        try {
                            if(activePerson == null) throw new NullPointerException();
                            System.out.print("Wybierz numer wolnego pomieszczenia aby wynająć: ");
                            choice = in.nextInt();
                            freeRoomList.get(choice-1).setTenant(activePerson);
                            if(freeRoomList.get(choice-1).getTenant() == activePerson) System.out.println("Wynajęto pomieszczenie o ID: "+freeRoomList.get(choice-1).getId());

                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Nieprawidłowy numer pomieszczenia");
                        }catch (NullPointerException e){
                            System.out.println("Nie wybrano żadnej osoby");
                        }
                        System.out.println("--------------------------------------------------------------");
                        break;
                    case 8:
                        magazine.saveToFile();
                        break;
                    default:
                        System.out.println("Nieprawidłowa wartość. Wybierz ponownie");
                }
            }catch (InputMismatchException e){
                System.out.println("Nieprawidłowa wartość. Wybierz ponownie");
                in.next();
            }
        }
    }
}