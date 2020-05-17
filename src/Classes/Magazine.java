package Classes;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Magazine {
    private String name;
    private List<Room> roomList;

    public Magazine(String name, int numberOfRooms) {
        this.name = name;
        roomList = new ArrayList<>(numberOfRooms);
    }

    public void addRoom(Room p){
        roomList.add(p);
    }

    public String toString(){
        sortRoomList();
        return "\nMAGAZYN-------------------------------------------------------------"+
               "\nNazwa: "+name+
               "\nLista pomieszczeń: "+roomList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    private void sortRoomList(){
        roomList.sort((r1, r2) -> r1.compareTo(r2));
    }

    public void saveToFile(){
        try{
            PrintWriter printWriter = new PrintWriter("stanMagazynu.txt");
            printWriter.print(this.toString());
            printWriter.close();
            System.out.println("Zapisano stan magazynu");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
