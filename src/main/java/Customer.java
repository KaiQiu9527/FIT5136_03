import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Customer is the entity class to represent customer in our program.
 * Customer is a derived class which extends User class.
 * It has the same attributes of the User class.
 *
 * @version 1.1
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
public class Customer extends User {

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone_no;
    }

    public void setPhone(String phone) {
        phone_no = phone;
    }

    public Customer(){};

    public Customer(String username, String fname, String lname, String dob, String password_hash, String email, String address, String phone_no, String id){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password_hash = password_hash;
        this.dob = dob;
        this.usertype = "customer";
        this.email = email;
        this.address = address;
        this.phone_no = phone_no;
        this.id = id;
    }
}
