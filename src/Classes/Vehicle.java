package Classes;

import Exceptions.AlreadyReducedException;

public abstract class Vehicle implements Reducible{
    private String name;
    private double volume;   // Objętość przedmiotu

    private boolean isReduced = false;  // Czy objętość została zredukowana
    private double reducer;  // Ile razy lub o ile można zredukować objętość

    Vehicle(String name, double volume){
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    void setReducer(double reducer){
        this.reducer = reducer;
    }

    public void reduceVolume(){
        try{
            if(!isReduced){
                volume = volume*reducer;
                isReduced = true;
            }
            else throw new AlreadyReducedException();
        }catch (AlreadyReducedException e){
            e.printStackTrace();
        }
    }
}
