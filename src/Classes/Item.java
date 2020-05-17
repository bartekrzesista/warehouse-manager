package Classes;

import Exceptions.AlreadyReducedException;

public class Item implements Reducible{
    private String name;
    private double volume;   // Objętość przedmiotu

    private boolean isReduced = false;  // Czy objętość została zredukowana
    private double reducer = 0.5;   // Ile razy lub o ile można zredukować objętość

    public Item(String name, double volume){
        this.name = name;
        this.volume = volume;
    }

    public Item(String name, double length, double width, double height){
        this.name = name;
        this.volume = length*width*height;
    }

    public String toString(){
        return "\n\t\tPRZEDMIOT ->"+
               " Nazwa: "+name+","+
               " Objętość w m3: "+volume;
    }

    public double getVolume() {
        return volume;
    }

    public String getName() {
        return name;
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
