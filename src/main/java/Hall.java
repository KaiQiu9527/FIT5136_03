/**
 * Hall is the entity class to represent hall in our program.
 * Hall has eight attributes, they are:
 * String name; it represents the name of the hall.
 * String location; it represents the location of the hall.
 * String description; it represents the description of the hall.
 * int hallId; it represents the hall id of the hall.
 * int ownerId; it represents the owner id of the hall.
 * String supportEventType; it represents the support event type of the hall.
 * double discount; it represents the discount (0.0~1.0) of hall, 0.0 means no discount.
 * String picture; it represents the filename of the hall's picture.
 * double price; it represents the standard price of the hall.
 *
 * @version 1.1
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
public class Hall {
    String name;
    String location;
    String description;
    int hallId;
    int ownerId;
    String supportEventType;
    double discount;
    String picture;
    double price;

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int id) {
        this.hallId = id;
    }

    public String getSupportEventType() {
        return supportEventType;
    }

    public void setSupportEventType(String supportEventType) {
        this.supportEventType = supportEventType;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
