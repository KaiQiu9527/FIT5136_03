import java.util.Date;
public class Booking {
    int bookingId;
    int customerId;
    int hallId;
    int ownerId;
    String eventType;
    int eventSize;
    Date startTime;
    Date endTime;
    boolean whetherCatering;
    String state;
    double price;

    public Booking(){

    }

    public Booking(Quotation quotation){
        this.customerId = quotation.getCustomerId();
        this.hallId = quotation.getHallId();
        this.ownerId = quotation.getOwnerId();
        this.eventType = quotation.getEventType();
        this.eventSize = quotation.getEventSize();
        this.startTime = quotation.getStartTime();
        this.endTime = quotation.getEndTime();
        this.whetherCatering = quotation.getWhetherCatering();
        this.state = "new";
        this.price = quotation.getPrice();
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingID) {
        this.bookingId = bookingID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerID) {
        this.customerId = customerID;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallID) {
        this.hallId = hallID;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerID) {
        this.ownerId = ownerID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean getWhetherCatering() {
        return whetherCatering;
    }

    public void setWhetherCatering(boolean whetherCatering) {
        this.whetherCatering = whetherCatering;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
