package Dao;

public class ParkingLot {
    private String parkingLotName;
    private int totalCapacity;

    private int capacity = 10;

    public ParkingLot(String name) {
        this.parkingLotName = name;
    }

    public ParkingLot(String name, Integer capacity) {
        this.parkingLotName = name;
        this.capacity = capacity;
        this.totalCapacity = capacity;
    }

    public Double getAvailablePositionRate() {
        return (double) this.capacity / this.totalCapacity;
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

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
