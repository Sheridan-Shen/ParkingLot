package Service.ParkingBoyService;

import Controller.Message;
import Dao.Car;
import Dao.ParkingLot;
import Dao.Ticket;
import Service.ParkingService;

import java.util.ArrayList;
import java.util.Objects;

public class ParkingBoy {
    private String boyName;
    private ArrayList<ParkingLot> parkingLots;
    ParkingService parkingService = new ParkingService();
    ParkStrategy parkStrategy;

    public ParkingBoy(String boyName, ArrayList<ParkingLot> parkingLots, ParkStrategy parkStrategy) {
        this.boyName = boyName;
        this.parkingLots = parkingLots;
        this.parkStrategy = parkStrategy;
    }

    public Message manualParkCar(Car car) {
        return parkStrategy.manualParkCar(car, parkingLots, parkingService);
    }

    public Message manualFetchCar(Ticket ticket) {
        String parkingLotName = ticket.getParkingLotName();
        ParkingLot targetParkingLot = null;
        for (ParkingLot parkinglot : parkingLots) {
            if (Objects.equals(parkinglot.getParkingLotName(), parkingLotName))
                targetParkingLot = parkinglot;
        }
        return parkingService.fetchCar(targetParkingLot, ticket);
    }
}
