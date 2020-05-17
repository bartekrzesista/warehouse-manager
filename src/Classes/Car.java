package Classes;

public class Car extends Vehicle {
    private String fuel;

    public Car(String name, double volume, String fuel) {
        super(name, volume);
        this.fuel = fuel;
        super.setReducer(0.8);
    }

    public String toString(){
        return "\n\t\tSAMOCHOD ->"+
               " Marka: "+super.getName()+","+
               " Objętość w m3: "+super.getVolume()+","+
               " Rodzaj paliwa: "+fuel;
    }

    public String getFuel() {
        return fuel;
    }
}
