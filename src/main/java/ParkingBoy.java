import java.util.ArrayList;

public class ParkingBoy {
    private String boyName;
    private ArrayList<ParkingLot> parkingLots;

    ParkingBoy(String boyName,ArrayList<ParkingLot> parkingLots){
        this.boyName = boyName;
        this.parkingLots = parkingLots;
    }


    public Message manualParkCar(Car car) {
        ParkingService parkingService = new ParkingService();
        return parkingService.parkCar(parkingLots.get(0),car);
    }
}
