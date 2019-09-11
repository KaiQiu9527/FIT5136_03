import java.io.*;
import java.util.*;

public class Main {

    String username = "";

    public static void main(String[] args) {
        Main main = new Main();
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        main.welcome();
    }

    public void welcome(){
        Text text = new Text();
        Scanner console = new Scanner(System.in);
        text.simpleTitle("Welecome to the Prime Events");
        text.displayInfo("   Select your choice:");
        text.displayInfo("1. Log in");
        text.displayInfo("2. New user sign up");
        text.displayInfo("3. Exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        try {
            int input = Integer.parseInt(console.nextLine());
            if (input == 1)
            {
                System.out.print('\u000C');
                login();
            }
            if (input == 2)
            {
                System.out.print('\u000C');
                register();
            }
            if (input == 3)
            {
                System.exit(0);
            }
        }catch (Exception e)
        {
            welcome();
        }




    }


    public void login()
    {
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        String username;
        String password_hash = "";
        text.simpleTitle("Welecome to the Prime Events");
        while (true) {
            text.displayInfo("Login:");
            text.displayInfo("Please enter username:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            username = console.nextLine();
            if (username.equals("")){
                text.displayInfo("Please enter username:");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                System.out.println();
                System.out.println();
            }
            else break;
        }

        while (true){
            text.simpleTitle("Welecome to the Prime Events");
            text.displayInfo("Login:");
            text.displayInfo("Please enter password:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            password_hash = String.valueOf(console.nextLine().hashCode());
            //read the file and check the username and password
            FileIO fileIO = new FileIO();
            if (fileIO.loginVerify(username,password_hash)) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Successfully!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                welcome();
            }
            else{
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Failed!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                welcome();
            }
        }

        //System.out.println("Verifying...");
    }

    public void register()
    {
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        User user;
        String password;
        String confirm_password;
        int type = -1;
        Map<String,String> usermap = new HashMap<>();
        //loop until the user input is correct
        while(true) {
            text.simpleTitle("Register");
            text.displayInfo("Please select your user type:");
            text.displayInfo("1. Customer");
            text.displayInfo("2. Owner");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            try{
                //if user input is not a correct number, return a warning
                type = Integer.parseInt(console.nextLine());
            }catch (Exception e){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            switch (type){
                case 1 :
                    user = new Customer();
                    break;
                case 2 :
                    user = new Owner();
                    break;
                default: //if user input an incorrect number, return a warning
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    text.displayInfo("Please select correctly!!!!!!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
            }
            break;
        }

        while(true) {
            text = new Text();
            text.simpleTitle("Register");
            text.displayInfo("Please enter your username:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            String username = console.nextLine();
            if (username.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the username correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setUsername(username);
            break;
        }


        while (true) {
            text = new Text();
            text.simpleTitle("Register");
            text.displayInfo("Please enter your password:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            password = console.nextLine();
            if (password.equals("")){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the password correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }else if (password.length() < 6 || password.length() > 15){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the password between 6 and 15 characters!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }

            text.simpleTitle("Register");
            text.displayInfo("Please enter your password again:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            confirm_password = console.nextLine();
            if (confirm_password.equals("")){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the password correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }else if (!confirm_password.equals(password)){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the same password!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            //hash password and store it
            int hash = password.hashCode();
            user.setPassword_hash(String.valueOf(hash));
            break;
        }

        while(true) {
            text = new Text();
            text.simpleTitle("Register");
            text.displayInfo("Please enter your firstname:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            String fname = console.nextLine();
            if (fname.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the firstname correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setFname(fname);
            break;
        }

        while(true) {
            text = new Text();
            text.simpleTitle("Register");
            text.displayInfo("Please enter your lastname:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            String lname = console.nextLine();
            if (lname.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the lastname correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setLname(lname);
            break;
        }

        while(true) {
            text = new Text();
            text.simpleTitle("Register");
            text.displayInfo("Please enter your email:");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            String email = console.nextLine();
            if (email.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the firstname correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }else if (!email.contains("@")){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                text.displayInfo("Please enter the email correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setEmail(email);
            break;
        }

        if (type == 1)
        {
            while(true) {
                text = new Text();
                text.simpleTitle("Register");
                text.displayInfo("Please enter your address:");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                String address = console.nextLine();
                if (address.equals("")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    text.displayInfo("Please enter the address correctly!!!!!!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
                user.setAddress(address);
                break;
            }

            while(true) {
                text = new Text();
                text.simpleTitle("Register");
                text.displayInfo("Please enter your phone number:");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                String phone_no = console.nextLine();
                if (phone_no.equals("")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    text.displayInfo("Please enter the address correctly!!!!!!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
                user.setPhone_no(phone_no);
                break;
            }

            user.setUsertype("customer");
        }
        else if (type == 2)
        {
            user.setUsertype("owner");
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
        //display the information after input
        text = new Text();
        text.simpleTitle("Register");
        text.displayInfo("These are the information of you: ");
        text.displayInfo("UserType: " + usermap.get("usertype"));
        text.displayInfo("Username: " + usermap.get("username"));
        text.displayInfo("Firstname: " + usermap.get("fname"));
        text.displayInfo("Lastname: " + usermap.get("lname"));
        text.displayInfo("Email: " + usermap.get("email"));
        if (type == 1){
            text.displayInfo("Address: " + usermap.get("address"));
            text.displayInfo("Phone NO: " + usermap.get("phone_no"));
        }
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");

        //write the map into a file
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("user.txt",true));
            pw.println(usermap);
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        //now turn to the login page
        text.displayInfo("What do you want to do now?:");
        text.displayInfo("1. Login");
        text.displayInfo("2. Press any other key to exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String operation = console.nextLine();
        if (operation.equals("1")){
            Main main = new Main();
            main.login();
        }
        else {
            System.exit(0);
        }
    }
}
