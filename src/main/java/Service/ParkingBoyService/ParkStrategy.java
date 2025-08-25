package Service.ParkingBoyService;

import Controller.Message;
import Dao.Car;
import Dao.ParkingLot;
import Dao.Ticket;
import Service.ParkingService;

import java.util.ArrayList;

public interface ParkStrategy {
    public Message manualParkCar(Car car, ArrayList<ParkingLot> parkingLots, ParkingService parkingService);
}
