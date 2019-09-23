import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends User {
    String address;
    int phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Customer(){};

    public Customer(String username, String fname, String lname, String dob, String password_hash, String email, String address, String phone_no, int id){
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
        this.id = String.valueOf(id);
    }
}
