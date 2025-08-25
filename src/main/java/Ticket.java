public class Ticket {
    private int ticketOid;
    private String parkingLotName;
    private String carName;

    Ticket(String parkingLotName, String carName, int ticketOid){
        this.parkingLotName = parkingLotName;
        this.carName = carName;
        this.ticketOid = ticketOid;
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

    public int getTicketOid() {
        return ticketOid;
    }
}
