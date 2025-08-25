import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    @Test
    public void test_given_parking_lot_and_a_car_when_park_then_success() {
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");
        Car car = new Car("myCar");
        ParkingService parkingService = new ParkingService();
        //When
        Message message = parkingService.parkCar(parkingLot, car);
        Ticket ticket = message.getTicket();
        String result = message.getResultMsg();
        //Then
        assertEquals("停车成功", result);
        assertEquals("parkingLot", ticket.getParkingLotName());
        assertEquals("myCar", ticket.getCarName());
    }


}
