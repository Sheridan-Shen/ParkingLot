public class Message {
    private String resultMsg;
    private Ticket ticket;
    private Car car;

    Message(String resultMsg,Ticket ticket){
        this.resultMsg = resultMsg;
        this.ticket = ticket;
    }

    Message(String resultMsg,Car car){
        this.resultMsg = resultMsg;
        this.car = car;
    }

    Message(String resultMsg){
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
