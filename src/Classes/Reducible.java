package Classes;

public interface Reducible extends Comparable<Reducible>{
    void reduceVolume();
    double getVolume();
    String getName();
    default int compareTo(Reducible r){
        if(this.getVolume() < r.getVolume()) return 1;
        else if(this.getVolume() == r.getVolume()) return 0;
        else return -1;
    }
}
