public class Hall {
    String name;
    String location;
    String description;
    int hallId;
    int ownerId;
    String supportEventType;
    double discount;
    String picture;

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
}
