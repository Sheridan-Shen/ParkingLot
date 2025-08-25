package Service;

import Controller.Message;
import Dao.Car;
import Dao.Ticket;

public interface ParkFetchStrategy {
    public Message manualParkCar(Car car);

    public Message manualFetchCar(Ticket ticket);
}
