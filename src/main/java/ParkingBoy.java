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
        ParkingLot targetParkingLot=parkingLots.get(0);
        for(ParkingLot parkinglot:parkingLots)
        {
            if(parkinglot.getCapacity()>targetParkingLot.getCapacity())
                targetParkingLot=parkinglot;
        }
        return parkingService.parkCar(targetParkingLot,car);
    }
}
