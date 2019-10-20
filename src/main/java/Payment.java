import java.util.Calendar;
import java.util.Date;

/**
 * Payment is the entity class to represent payment in our program.
 * Payment has seven attributes, they are:
 * int paymentId; it represents the id of the payment.
 * int BookingId; it represents the related booking id.
 * int userId; it represents the user id of paid customer.
 * Date paidDateTime; it represents the paid time of the payment.
 * double price; it represents the paid price of the payment.
 * String paymentState;  it represents the state of the payment.
 *
 * @version 1.1
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
public class Payment {
    int paymentId;
    int BookingId;
    int userId;
    Date paidDateTime = new Date();
    double price;
    String paymentState = "paid";

    public Payment(){
    }

    public Payment(Booking booking){
        this.BookingId = booking.getBookingId();
        this.price = booking.getPrice();
        this.userId = booking.getCustomerId();
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public Date getPaidDateTime() {
        return paidDateTime;
    }

    public void setPaidDateTime(Date paidDateTime) {
        this.paidDateTime = paidDateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
