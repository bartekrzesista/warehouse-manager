package Classes;

import Exceptions.NeverRentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String surname;
    private String pesel;
    private String address;
    private LocalDate birthDate;
    LocalDate firstRentDate;
    private List<Room> roomList= new ArrayList<>();

    public Person(String name, String surname, String pesel, String address, LocalDate birthDate, LocalDate firstRentDate) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.birthDate = birthDate;
        this.firstRentDate = firstRentDate;
    }

    public Person(String name, String surname, String pesel, String address, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return name+" "+surname+" ("+pesel+")";

    }

    private String isFirstRentDateNull(){
        return firstRentDate == null ? "Brak" : firstRentDate.toString();
    }

    public String getPersonalData(){
        return "\nOSOBA"+
               "\nImię i nazwisko: "+name+" "+surname+
               "\nPesel: "+pesel+
               "\nAdres: "+address+
               "\nData urodzenia: "+birthDate+
               "\nData pierwszego najmu: "+isFirstRentDateNull();
    }

    public LocalDate getFirstRentDate() {
        try{
            if(this.firstRentDate == null) throw new NeverRentException();
        }catch(NeverRentException e){
            e.printStackTrace();
        }
        return this.firstRentDate;
    }

    public void setFirstRentDate(LocalDate rentDate) {
        firstRentDate = rentDate;
    }

    void addRoom(Room p) {
        roomList.add(p);
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
