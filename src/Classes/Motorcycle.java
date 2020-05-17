package Classes;

public class Motorcycle extends Vehicle{
    private boolean approval;  // Homologacja

    public Motorcycle(String name, double volume, boolean approval){
        super(name, volume);
        super.setReducer(0.9);
        this.approval = approval;
    }

    public String toString(){
        return "\n\t\tMOTOCYKL ->"+
               " Marka: "+super.getName()+","+
               " Objętość w m3: "+super.getVolume()+","+
               " Homologacja: "+isApproved();
    }

    private String isApproved(){
        return approval ? "Tak" : "Nie";
    }
}
