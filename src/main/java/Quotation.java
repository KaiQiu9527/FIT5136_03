import java.util.Date;
public class Quotation {
    int quotationId;
    int customerId;
    int hallId;
    int ownerId;
    String eventType;
    int eventSize;
    Date startTime;
    Date endTime;
    boolean whetherCatering;
    String state;
    int price;

    public int getQuotationId() {
        return quotationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getHallId() {
        return hallId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getEventType() {
        return eventType;
    }

    public int getEventSize() {
        return eventSize;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean isWhetherCatering() {
        return whetherCatering;
    }

    public String getState() {
        return state;
    }

    public int getPrice() {
        return price;
    }

    public void setQuotationId(int quotationId) {
        this.quotationId = quotationId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setWhetherCatering(boolean whetherCatering) {
        this.whetherCatering = whetherCatering;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


