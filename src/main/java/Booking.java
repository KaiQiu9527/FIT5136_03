import java.util.Date;

/**
 * Booking is the entity class to represent a booked event in our program.
 * A booking object being generated means the quotation is confirmed and payment is made.
 * Booking has eleven attributes, they are:
 * int bookingId; it represents the id of the booking.
 * int customerId; it represent the related customer id.
 * int hallId; it represent the related hall id.
 * int ownerId; it represent the related owner id.
 * String eventType; it represent the event type of the booking.
 * int eventSize; it represent the event size of the booking.
 * Date startTime; it represent the start time of the booking.
 * Date endTime; it represent the end time of the booking.
 * boolean whetherCatering; it represent whether this booked event requires catering.
 * String state; it represent the state of the booking.
 * double price; it represent the price of the booking.
 *
 * @version 1.1
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
public class Booking {
    int bookingId = 0;
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
