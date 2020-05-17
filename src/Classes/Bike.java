package Classes;

public class Bike extends Vehicle{
    private int derailleurs;  // Liczba przerzutek

    public Bike(String name, double volume, int derailleurs){
        super(name, volume);
        super.setReducer(0.5);
        this.derailleurs = derailleurs;
    }

    public String toString(){
        return "\n\t\tROWER ->"+
               " Marka: "+super.getName()+","+
               " Objętość w m3: "+getVolume()+","+
               " Liczba przerzutek: "+derailleurs;
    }
}
