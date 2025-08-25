public class Ticket {
    private String parkingLotName;
    private String carName;
    private boolean isUsed;

    Ticket(String parkingLotName, String carName){
        this.parkingLotName = parkingLotName;
        this.carName = carName;
        this.isUsed = false;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
