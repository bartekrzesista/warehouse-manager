package Exceptions;

public class TenantSetException extends Exception{
    public String toString(){
        return "OwnerSetException: To pomieszczenie jest obecnie wynajmowane";
    }
}
