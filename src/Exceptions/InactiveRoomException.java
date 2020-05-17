package Exceptions;

public class InactiveRoomException extends Exception{
    public String toString(){
        return "InactiveRoomException: To pomieszczenie jest obecnie wyłączone z użytkowania";
    }
}
