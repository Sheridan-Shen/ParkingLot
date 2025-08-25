import Controller.Message;
import Dao.Car;
import Service.ParkingBoyService.Impl.SmartParkingBoyStrategy;
import Service.ParkingBoyService.Impl.StandardParkingBoyStrategy;
import Service.ParkingBoyService.Impl.SuperParkingBoyStrategy;
import Service.ParkingBoyService.ParkingBoy;
import Dao.ParkingLot;
import Dao.Ticket;
import Service.ParkingService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        assertEquals("No available position", result2);
    }

    @Test
    public void test_given_parking_lot_and_a_parked_car_when_park_then_failure(){
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");
        Car car = new Car("myCar");
        ParkingService parkingService = new ParkingService();
        //When
        Message message1 = parkingService.parkCar(parkingLot, car);
        Message message2 = parkingService.parkCar(parkingLot, car);
        String result1 = message1.getResultMsg();
        String result2 = message2.getResultMsg();
        //Then
        assertEquals("停车成功", result1);
        assertEquals("该车已停放", result2);
    }

    @Test
    public void test_given_parking_lot_and_null_car_when_park_then_failure(){
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");

        ParkingService parkingService = new ParkingService();
        //When
        Message message1 = parkingService.parkCar(parkingLot, null);
        String result1 = message1.getResultMsg();
        //Then
        assertEquals("停车失败", result1);
    }

    @Test
    public void test_given_parking_lot_and_ticket_when_fetch_then_success(){
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");
        Car car = new Car("myCar");
        ParkingService parkingService = new ParkingService();
        Message message1 = parkingService.parkCar(parkingLot, car);
        Ticket ticket = message1.getTicket();
        //When
        Message resMessage = parkingService.fetchCar(parkingLot,ticket);
        String result2 = resMessage.getResultMsg();
        //Then
        assertEquals("取车成功", result2);
        assertEquals(car,resMessage.getCar());
    }

    @Test
    public void test_given_parking_lot_and_wrong_ticket_when_fetch_then_failure(){
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");
        Car car = new Car("myCar");
        ParkingService parkingService = new ParkingService();
        Message message1 = parkingService.parkCar(parkingLot, car);
        Ticket ticket = message1.getTicket();
        Ticket wrongTicket = new Ticket(parkingLot.getParkingLotName(), car.getCarName(), 12345678);

        //When
        Message resMessage = parkingService.fetchCar(parkingLot,wrongTicket);

        //Then
        assertEquals("Unrecognized parking ticket.", resMessage.getResultMsg());
    }

    @Test
    public void test_given_parking_lot_without_ticket_when_fetch_then_failure(){
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");
        Car car = new Car("myCar");
        ParkingService parkingService = new ParkingService();
        Message message1 = parkingService.parkCar(parkingLot, car);
        Ticket ticket = message1.getTicket();

        //When
        Message resMessage = parkingService.fetchCar(parkingLot,null);

        //Then
        assertEquals("没有Ticket", resMessage.getResultMsg());
    }

    @Test
    public void test_given_parking_lot_used_ticket_when_fetch_then_failure(){
        //Given
        ParkingLot parkingLot = new ParkingLot("parkingLot");
        Car car = new Car("myCar");
        ParkingService parkingService = new ParkingService();
        Message message1 = parkingService.parkCar(parkingLot, car);
        Ticket ticket = message1.getTicket();

        //When
        Message resMessage1 = parkingService.fetchCar(parkingLot,ticket);
        Message resMessage2 = parkingService.fetchCar(parkingLot,ticket);

        //Then
        assertEquals("Unrecognized parking ticket.", resMessage2.getResultMsg());
    }

    @Test
    public void test_given_parking_lot_and_a_car_and_a_boy_when_park_then_success() {
        //Given
        ParkingLot parkingLot1=new ParkingLot("parkingLot1",20);
        ParkingLot parkingLot2=new ParkingLot("parkingLot2",10);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car = new Car("myCar");
        //When
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SmartParkingBoyStrategy());
        Message message = parkingBoy.manualParkCar(car);
        Ticket ticket = message.getTicket();
        String result = message.getResultMsg();
        //Then
        assertEquals("停车成功", result);
        assertEquals("parkingLot1", ticket.getParkingLotName());
        assertEquals("myCar", ticket.getCarName());
    }

    @Test
    public void test_given_a_full_parking_lot_and_an_available_parking_lot_and_a_car_and_a_boy_when_park_then_success() {
        //Given
        ParkingLot parkingLot1=new ParkingLot("parkingLot1",5);
        ParkingLot parkingLot2=new ParkingLot("parkingLot2",10);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car = new Car("myCar");
        //When
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SmartParkingBoyStrategy());
        Message message = parkingBoy.manualParkCar(car);
        Ticket ticket = message.getTicket();
        String result = message.getResultMsg();
        //Then
        assertEquals("停车成功", result);
        assertEquals("parkingLot2", ticket.getParkingLotName());
        assertEquals("myCar", ticket.getCarName());
    }

    @Test
    public void test_given_parking_lot_and_ticket_with_boy_when_fetch_then_success(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot1");
        ParkingLot parkingLot2 = new ParkingLot("parkingLot2");
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car = new Car("myCar");
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SmartParkingBoyStrategy());
        Message message = parkingBoy.manualParkCar(car);
        Ticket ticket = message.getTicket();

        //When
        Message resMessage = parkingBoy.manualFetchCar(ticket);
        String result2 = resMessage.getResultMsg();
        //Then
        assertEquals("取车成功", result2);
        assertEquals(car,resMessage.getCar());
    }

    @Test
    public void test_given_parking_lot_and_wrong_ticket_with_boy_when_fetch_then_failure(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot1");
        ParkingLot parkingLot2 = new ParkingLot("parkingLot2");
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car = new Car("myCar");
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SmartParkingBoyStrategy());
        Message message = parkingBoy.manualParkCar(car);
        Ticket ticket = message.getTicket();

        Ticket wrongTicket = new Ticket(ticket.getParkingLotName(), car.getCarName(), 12345678);

        //When
        Message resMessage = parkingBoy.manualFetchCar(wrongTicket);
        String result = resMessage.getResultMsg();
        //Then
        assertEquals("Unrecognized parking ticket.", result);
    }

    @Test
    public void test_given_parking_lot_and_used_ticket_with_boy_when_fetch_then_failure(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot1");
        ParkingLot parkingLot2 = new ParkingLot("parkingLot2");
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car = new Car("myCar");
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SmartParkingBoyStrategy());
        Message message = parkingBoy.manualParkCar(car);
        Ticket ticket = message.getTicket();

        //When
        Message resMessage1 = parkingBoy.manualFetchCar(ticket);
        Message resMessage2 = parkingBoy.manualFetchCar(ticket);
        String result2 = resMessage2.getResultMsg();
        //Then
        assertEquals("Unrecognized parking ticket.", result2);
    }

    @Test
    public void test_given_two_full_parking_lot_and_ticket_with_boy_when_park_then_failure(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot1",0);
        ParkingLot parkingLot2 = new ParkingLot("parkingLot2",0);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car = new Car("myCar");
        //When
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SmartParkingBoyStrategy());
        Message message = parkingBoy.manualParkCar(car);

        //Then
        assertEquals("No available position", message.getResultMsg());
    }

    @Test
    public void test_super_parking_boy_strategy(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot1",15);
        ParkingLot parkingLot2 = new ParkingLot("parkingLot2",20);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car1 = new Car("myCar1");
        Car car2 = new Car("myCar2");
        Car car3 = new Car("myCar3");
        Car car4 = new Car("myCar4");
        //When
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new SuperParkingBoyStrategy());

        //Then
        parkingBoy.manualParkCar(car1);
        assertEquals(14, parkingLot1.getCapacity());
        parkingBoy.manualParkCar(car2);
        assertEquals(19, parkingLot2.getCapacity());
        parkingBoy.manualParkCar(car3);
        assertEquals(18, parkingLot2.getCapacity());
        parkingBoy.manualParkCar(car4);
        assertEquals(13, parkingLot1.getCapacity());
    }

    @Test
    public void test_standard_parking_boy_strategy(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot1",1);
        ParkingLot parkingLot2 = new ParkingLot("parkingLot2",2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        Car car1 = new Car("myCar1");
        Car car2 = new Car("myCar2");
        Car car3 = new Car("myCar3");
        //When
        ParkingBoy parkingBoy=new ParkingBoy("boy",parkingLots, new StandardParkingBoyStrategy());

        //Then
        parkingBoy.manualParkCar(car1);
        assertEquals(0, parkingLot1.getCapacity());
        parkingBoy.manualParkCar(car2);
        assertEquals(1, parkingLot2.getCapacity());
        parkingBoy.manualParkCar(car3);
        assertEquals(0, parkingLot2.getCapacity());

    }

}
