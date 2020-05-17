package Exceptions;

public class NeverRentException extends Exception {
    public String toString(){
        return "NeverRentException: Ta osoba nigdy nie wynajmowała pomieszczenia";
    }
}
