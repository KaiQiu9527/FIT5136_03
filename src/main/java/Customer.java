import java.text.SimpleDateFormat;
import java.util.Date;

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
