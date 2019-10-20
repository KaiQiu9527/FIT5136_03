/**
 * Admin is the entity class to represent administrator in our program.
 * Admin is a derived class which extends User class.
 * It has the same attributes of the User class.
 *
 * @version 1.1
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
public class Admin extends User {
    public Admin(){
        username = "admin";
        fname = "";
        lname = "";
        dob = "";
        usertype = "admin";
        password_hash = "1450575459";
        email = "";
        address = "";
        phone_no = "";
        id = "1";

    };
}
