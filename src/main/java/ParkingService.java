import java.util.HashMap;
import java.util.Objects;

public class ParkingService {
    private HashMap<Integer, Boolean> hashMap = new HashMap<>();
    public final static String PARK_SUCCESS = "停车成功";

    ParkingService() {
    }

    public Message parkCar(ParkingLot parkingLot, Car car) {
        Ticket ticket = new Ticket(parkingLot.getParkingLotName(), car.getCarName());
        int tickedOid = getHashCode(parkingLot, car);
        hashMap.put(tickedOid, true);
        Message message = new Message(PARK_SUCCESS, ticket);
        return message;
    }

    private int getHashCode(ParkingLot parkingLot, Car car) {
        Long parkTime = System.currentTimeMillis();
        return Objects.hash(parkTime, (long) (parkingLot.hashCode()), (long) (car.hashCode()));
    }
}
