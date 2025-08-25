package Controller;

import Dao.Car;
import Dao.Ticket;

public class Message {
    private String resultMsg;
    private Ticket ticket;
    private Car car;

    public Message(String resultMsg, Ticket ticket){
        this.resultMsg = resultMsg;
        this.ticket = ticket;
    }

    public Message(String resultMsg,Car car){
        this.resultMsg = resultMsg;
        this.car = car;
    }

    public Message(String resultMsg){
        this.resultMsg = resultMsg;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Car getCar() {
        return car;
    }
}
