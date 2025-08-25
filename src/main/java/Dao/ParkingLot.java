package Dao;

public class ParkingLot {
    private String parkingLotName;

    private int capacity = 10;

    public ParkingLot(String name){
        this.parkingLotName = name;
    }

    public ParkingLot(String name, Integer capacity){
        this.parkingLotName = name;
        this.capacity=capacity;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }
}
