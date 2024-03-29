/**
 * User class is the entity class to represent all kinds of users, including administrator,
 * customer and owner.
 * User class is the base class for Admin, Customer and Owner.
 * User class has nine attributes, they are:
 * String username; it represents the username of the user.
 * String fname; it represents the first name of the user.
 * String lname; it represents the last name of the user.
 * String dob; it represents the date of birth of the user.
 * String usertype; it represents the usertype of the user, and it must be admin,
 * customer or owner.
 * String password_hash; it represents the hash value of user's password.
 * String email; it represents the email of the user.
 * String address = ""; it represents the address of the user.
 * String phone_no = ""; it represents the phone number of the user.
 * String id; it represents the user id of the user.
 *
 * @version 1.4
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */

public abstract class User {
    String username;
    String fname;
    String lname;
    String dob;
    String usertype;
    String password_hash;
    String email;
    String address = "";
    String phone_no = "";
    String id;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    String discount = "0.0";

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
