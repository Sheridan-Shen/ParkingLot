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

    ParkingService() {
    }

    public Message parkCar(ParkingLot parkingLot, Car car) {
        if(parkingLot.getCapacity()<=0)
        {
            Message message=new Message(PARK_FAILURE);
            return  message;
        }
        if (parkedCar.contains(car)){
            Message message=new Message(DUPLICATE);
            return  message;
        }
        if(car==null)
        {
            Message message=new Message(PARK_FAILURE);
            return message;
        }
        int tickedOid = getHashCode(parkingLot, car);
        Ticket ticket = new Ticket(parkingLot.getParkingLotName(), car.getCarName(), tickedOid);

        hashMap.put(tickedOid, true);
        parkedCar.add(car);
        parkingLot.setCapacity(parkingLot.getCapacity()-1);
        Message message = new Message(PARK_SUCCESS, ticket);
        return message;
    }

    private int getHashCode(ParkingLot parkingLot, Car car) {
        Long parkTime = System.currentTimeMillis();
        return Objects.hash(parkTime, (long) (parkingLot.hashCode()), (long) (car.hashCode()));
    }

    public Message fetchCar(ParkingLot parkingLot, Ticket ticket) {
        int ticketOid = ticket.getTicketOid();
        if (!hashMap.containsKey(ticketOid)){
            return null;
        }
        String carName = ticket.getCarName();
        Car myCar = null;
        for (Car car: parkedCar) {
            if (Objects.equals(car.getCarName(), carName)){
                myCar = car;
            }
        }
        Message message = new Message(FETCH_SUCCESS,myCar);
        return message;
    }
}
