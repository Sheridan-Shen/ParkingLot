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

    @Test
    public void test_given_parking_lot_and_a_car_and_no_position_when_park_then_failure() {
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot", 1);
        Car car1 = new Car("myCar1");
        Car car2 = new Car("myCar2");
        ParkingService parkingService = new ParkingService();
        //When
        Message message1 = parkingService.parkCar(parkingLot, car1);
        Message message2 = parkingService.parkCar(parkingLot, car2);
        String result1 = message1.getResultMsg();
        String result2 = message2.getResultMsg();
        //Then
        assertEquals("停车成功", result1);
        assertEquals("停车失败", result2);
    }


}
