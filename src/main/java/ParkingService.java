import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ParkingService {
    private HashMap<Integer, Boolean> hashMap = new HashMap<>();
    private ArrayList<Car> parkedCar = new ArrayList<>();
    public final static String PARK_SUCCESS = "停车成功";
    public final static String PARK_FAILURE = "停车失败";
    public final static String DUPLICATE = "该车已停放";
    public final static String FETCH_SUCCESS = "取车成功";
    public final static String WRONG_TICKET = "Unrecognized parking ticket.";
    public final static String NO_TICKET = "没有Ticket";
    public final static String USED_TICKET = "Unrecognized parking ticket.";

    ParkingService() {
    }

    public Message parkCar(ParkingLot parkingLot, Car car) {
        if (parkingLot.getCapacity() <= 0) {
            return new Message(PARK_FAILURE);
        }
        if (parkedCar.contains(car)) {
            return new Message(DUPLICATE);
        }
        if (car == null) {
            return new Message(PARK_FAILURE);
        }
        int tickedOid = getHashCode(parkingLot, car);
        Ticket ticket = new Ticket(parkingLot.getParkingLotName(), car.getCarName(), tickedOid);

        hashMap.put(tickedOid, true);
        parkedCar.add(car);
        parkingLot.setCapacity(parkingLot.getCapacity() - 1);
        return new Message(PARK_SUCCESS, ticket);
    }

    private int getHashCode(ParkingLot parkingLot, Car car) {
        Long parkTime = System.currentTimeMillis();
        return Objects.hash(parkTime, (long) (parkingLot.hashCode()), (long) (car.hashCode()));
    }

    public Message fetchCar(ParkingLot parkingLot, Ticket ticket) {
        if (ticket == null) {
            return new Message(NO_TICKET);
        }
        int ticketOid = ticket.getTicketOid();
        if (!hashMap.containsKey(ticketOid)) {
            return new Message(WRONG_TICKET);
        }
        if (!hashMap.get(ticketOid)) {
            return new Message(USED_TICKET);
        }
        String carName = ticket.getCarName();
        Car myCar = null;
        for (Car car : parkedCar) {
            if (Objects.equals(car.getCarName(), carName)) {
                myCar = car;
            }
        }
        parkedCar.remove(myCar);
        hashMap.put(ticketOid, false);
        return new Message(FETCH_SUCCESS, myCar);
    }
}
