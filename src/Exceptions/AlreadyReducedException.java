package Exceptions;

public class AlreadyReducedException extends Exception {
    public String toString(){
        return "AlreadyReducedException: Nie można bardziej zredukować objętości tego elementu";
    }
}
