

public class Owner extends User {

    public Owner(){

    }


    public Owner(String username, String fname, String lname, String dob, String password_hash, String email, String address, String phone_no, int id){
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password_hash = password_hash;
        this.dob = dob;
        this.usertype = "owner";
        this.email = email;
        this.address = address;
        this.phone_no = phone_no;
        this.id = String.valueOf(id);
    }
}
