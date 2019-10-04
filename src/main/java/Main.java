import java.io.*;
import java.util.*;

public class Main {

//    static Customer customer;
//    static Admin admin;
//    static Owner owner;
    static private FileIO fileIO = new FileIO();
    static private User user;
    static private UI ui = new UI();
    static private ArrayList<Hall> halls;
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        Main main = new Main();
        main.welcome();
    }
    /*
    **This is the welcome page, all the operations start from here
     */

    public void welcome(){
        fileIO.startup();
        Scanner console = new Scanner(System.in);
        ui.loginPage();
        try {
            int input = Integer.parseInt(console.nextLine());
            switch (input){
                case 1:
                    System.out.print('\u000C');
                    login();
                    break;
                case 2:
                    System.out.print('\u000C');
                    register();
                    break;
                case 3:
                    System.out.print("Please Wait!");
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    welcome();
                    break;
            }
        }catch (Exception e)
        {
            //e.printStackTrace();
            welcome();
        }
    }


    public void login()
    {
        Scanner console = new Scanner(System.in);
        String username;
        String password_hash = "";
        while (true) {
            ui.loginStep("username");
            username = console.nextLine();
            if (username.equals(""))
                ui.loginStep("username");
            else if (username.toUpperCase().equals("Q"))
                welcome();
            else break;
        }

        while (true){
            ui.loginStep("password");
            password_hash = String.valueOf(console.nextLine().hashCode());
            //read the file and check the username and password
            user = fileIO.loginVerify(username,password_hash);
            if (user != null) {
                ui.loginVerification(true);
                String usertype = user.getUsertype();
                if (usertype.equals("customer")) {
                    CustomerMain customerMain = new CustomerMain(user);
                    customerMain.welcome();
                    break;
                }
                else if (usertype.equals("owner")) {
                    OwnerMain ownerMain = new OwnerMain(user);
                    ownerMain.welcome();
                    //
                    break;
                }
                if (usertype.equals("admin")) {
                    AdminMain adminMain = new AdminMain(user);
                    adminMain.welcome();
                    break;
                }
            }
            else{
                ui.loginVerification(false);
                welcome();
                break;
            }
        }

        //System.out.println("Verifying...");
    }

    public void register()
    {
        Scanner console = new Scanner(System.in);
        User user;
        String password;
        String confirm_password;
        int type = -1;
        Map<String,String> usermap = new HashMap<>();
        //loop until the user input is correct
        while(true) {
            ui.registerPage();
            try{
                //if user input is not a correct number, return a warning
                type = Integer.parseInt(console.nextLine());
            }catch (Exception e){
                continue;
            }
            switch (type){
                case 1 :
                    user = new Customer();
                    user.setUsertype("customer");
                    break;
                case 2 :
                    user = new Owner();
                    user.setUsertype("owner");
                    break;
                default: //if user input an incorrect number, return a warning
                    continue;
            }
            break;
        }

        while(true) {
            ui.registerEnter("username");
            String username = console.nextLine();
            if (username.equals(""))
                continue;
            user.setUsername(username);
            break;
        }


        while (true) {
            ui.registerEnter("password");
            password = console.nextLine();
            if (password.equals(""))
                continue;
            if (password.length() < 6 || password.length() > 15){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the password between 6 and 15 characters!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }

            ui.registerEnter("password again");
            confirm_password = console.nextLine();
            if (confirm_password.equals(""))
                continue;
            if (!confirm_password.equals(password)){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the same password!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            //hash password and store it
            int hash = password.hashCode();
            user.setPassword_hash(String.valueOf(hash));
            break;
        }

        while(true) {
            ui.registerEnter("first name");
            String fname = console.nextLine();
            if (fname.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the firstname correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setFname(fname);
            break;
        }

        while(true) {
            ui.registerEnter("last name");
            String lname = console.nextLine();
            if (lname.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the lastname correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setLname(lname);
            break;
        }

        while(true) {
            ui.registerEnter("email");
            String email = console.nextLine();
            if (email.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the email correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }else if (!email.contains("@") || email.length() <= 3){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the email correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setEmail(email);
            break;
        }

        if (type == 1 || type == 2)
        {
            while(true) {
                ui.registerEnter("address");
                String address = console.nextLine();
                if (address.equals("")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Please enter the address correctly!!!!!!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
                user.setAddress(address);
                break;
            }

            while(true) {
                ui.registerEnter("phone no");
                String phone_no = console.nextLine();
                if (phone_no.equals("")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Please enter the phone no correctly!!!!!!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
                user.setPhone_no(phone_no);
                break;
            }
        }

        //use a HashMap to store user's information
        usermap.put("usertype",user.getUsertype());
        usermap.put("username",user.getUsername());
        usermap.put("password",user.getPassword_hash());
        usermap.put("fname",user.getFname());
        usermap.put("lname",user.getLname());
        usermap.put("email",user.getEmail());
        usermap.put("address",user.getAddress());
        usermap.put("phone_no",user.getPhone_no());
        usermap.put("id", String.valueOf(new FileIO().getUserAmount()+1));
        //display the information after input
        ui.registerSuccess();
        ui.displayInfo("These are the information of you: ");
        ui.displayInfo("User ID: " + usermap.get("id"));
        ui.displayInfo("UserType: " + usermap.get("usertype"));
        ui.displayInfo("Username: " + usermap.get("username"));
        ui.displayInfo("Firstname: " + usermap.get("fname"));
        ui.displayInfo("Lastname: " + usermap.get("lname"));
        ui.displayInfo("Email: " + usermap.get("email"));
        ui.displayInfo("Address: " + usermap.get("address"));
        ui.displayInfo("Phone NO: " + usermap.get("phone_no"));
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");

        //write the map into a file
        fileIO.register(usermap);
        //now turn to the login page
        ui.displayInfo("What do you want to do now?:");
        ui.displayInfo("1. Back to home screen");
        ui.displayInfo("2. Press any other key to exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String operation = console.nextLine();
        if (operation.equals("1")){
            welcome();
        }
        else {
            System.exit(0);
        }
    }
}
