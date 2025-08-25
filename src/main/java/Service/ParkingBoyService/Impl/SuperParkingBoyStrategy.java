package Service.ParkingBoyService.Impl;

import Controller.Message;
import Dao.Car;
import Dao.ParkingLot;
import Service.ParkingBoyService.ParkStrategy;
import Service.ParkingService;

import java.util.ArrayList;

public class SuperParkingBoyStrategy implements ParkStrategy {

    @Override
    public Message manualParkCar(Car car, ArrayList<ParkingLot> parkingLots, ParkingService parkingService) {
        ParkingLot targetParkingLot = parkingLots.get(0);
        for (ParkingLot parkinglot : parkingLots) {
            if (parkinglot.getAvailablePositionRate() > targetParkingLot.getAvailablePositionRate()){
                targetParkingLot = parkinglot;
            }
        }
        return parkingService.parkCar(targetParkingLot, car);
    }
}
