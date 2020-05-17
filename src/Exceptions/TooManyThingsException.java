package Exceptions;

public class TooManyThingsException extends Exception {
    public String toString(){
        return "TooManyThingsException: Pomieszczenie nie jest w stanie pomieścić tego elementu";
    }
}
