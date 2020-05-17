package Classes;

import Exceptions.TenantSetException;
import Exceptions.InactiveRoomException;
import Exceptions.TooManyThingsException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room implements Comparable<Room>{
    private int Id;
    private boolean activity = true;
    private Person tenant;
    private final double maxCapacity;   // Pojemność pomieszczenia
    private double occupiedCapacity = 0;  // Aktualnie zajęta pojemność
    private List<Item> itemList = new ArrayList<>();
    private List<Vehicle> vehicleList = new ArrayList<>();
    private List<Reducible> elementList = new ArrayList<>();  //Łączna lista przedmiotów i pojazdów

    public Room(double maxCapacity) {
        this.Id = this.hashCode();
        this.maxCapacity = maxCapacity;
    }

    public Room(double length, double width, double height) {
        this.Id = this.hashCode();
        this.maxCapacity = length*width*height;
    }

    public String toString(){
        return "\n\n\tPOMIESZCZENIE ->"+
               " Id: "+Id+","+
               " Rozmiar w m3: "+maxCapacity+","+
               " Aktywność: "+isActive()+","+
               " Właściciel: "+tenantIsNull()+
               "\n\tLista przedmiotów: "+getElementList();
    }

    private void sortElementList(){
        elementList.sort((p1, p2) -> {
            if(p1.compareTo(p2) == 0) return p1.getName().compareTo(p2.getName());
            else return p1.compareTo(p2);
        });
    }

    // Zwracanie odpowiednio posortowanej listy
    public List<Reducible> getElementList() {
        sortElementList();
        return elementList;
    }

    private String tenantIsNull(){
        return tenant == null ? "Brak" : tenant.toString();
    }

    public String isActive(){
        return activity ? "Tak" : "Nie";
    }

    public void setActivity(boolean activity) {
        try {
            if(tenant == null) this.activity = activity;
            else throw new TenantSetException();
        }catch(TenantSetException e) {
            e.printStackTrace();
        }
    }

    public boolean getActivity() {
        return activity;
    }

    public Person getTenant() {
        return tenant;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setTenant(Person tenant){
        try {
            if(activity) {
                if(this.tenant == null){
                    this.tenant = tenant;
                    tenant.addRoom(this);
                    if(tenant.firstRentDate == null) tenant.setFirstRentDate(LocalDate.now());
                }
                else throw new TenantSetException();
            }
            else throw new InactiveRoomException();
        }catch (InactiveRoomException | TenantSetException e) {
            e.printStackTrace();
        }
    }

    public void removeTenant(){
        tenant = null;
        elementList.clear();
    }

    public void addElement(Item item){
        try{
            double temp = occupiedCapacity;
            temp += item.getVolume();
            if(temp <= maxCapacity){
                itemList.add(item);
                elementList.add(item);
                occupiedCapacity = temp;
            }
            else throw new TooManyThingsException();
        }catch(TooManyThingsException e){
            e.printStackTrace();
        }
    }

    public void removeElement(Item item){
        itemList.remove(item);
        elementList.remove(item);
        occupiedCapacity -= item.getVolume();
    }

    public void addElement(Vehicle vehicle){
        try{
            double temp = occupiedCapacity;
            temp += vehicle.getVolume();
            if(temp <= maxCapacity){
                vehicleList.add(vehicle);
                elementList.add(vehicle);
                occupiedCapacity = temp;
            }
            else throw new TooManyThingsException();
        }catch(TooManyThingsException e){
            e.printStackTrace();
        }
    }

    public void removeElement(Vehicle vehicle){
        vehicleList.remove(vehicle);
        elementList.remove(vehicle);
        occupiedCapacity -= vehicle.getVolume();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public int getId() {
        return Id;
    }

    public double getOccupiedCapacity(){
        return occupiedCapacity;
    }

    @Override
    public int compareTo(Room o) {
        if(maxCapacity > o.maxCapacity) return 1;
        else if(maxCapacity == o.maxCapacity) return 0;
             else return -1;
    }
}
