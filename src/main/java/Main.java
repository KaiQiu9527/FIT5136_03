import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    //    static Customer customer;
//    static Admin admin;
//    static Owner owner;
    static private FileIO fileIO = new FileIO();
    static private User user;
    static private UI ui = new UI();
    static private Hall hall = new Hall();
    //static private ArrayList<Hall> halls = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.welcome();
    }

    /**
     * This is the welcome page, all the operations start from here
     */
    public void welcome() {
        fileIO.startup();
        ui.loginPage();
        try {
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
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
        } catch (Exception e) {
            //e.printStackTrace();
            welcome();
        }
    }


    public void login() {
        String username;
        String password_hash = "";
        while (true) {
            ui.loginStep("username");
            username = sc.nextLine();
            if (username.equals(""))
                ui.loginStep("username");
            else if (username.equals("admin"))
                AdminWelcome();
            else if (username.toUpperCase().equals("Q"))
                welcome();
            else break;
        }

        while (true) {
            ui.loginStep("password");
            password_hash = String.valueOf(sc.nextLine().hashCode());
            //read the file and check the username and password
            user = fileIO.loginVerify(username, password_hash);
            if (user != null) {
                ui.loginVerification(true);
                String usertype = user.getUsertype();
                if (usertype.equals("customer")) {
                    CustomerWelcome();
                    break;
                } else if (usertype.equals("owner")) {
                    OwnerWelcome();
                    //
                    break;
                }
                if (usertype.equals("admin")) {
                    AdminWelcome();
                    break;
                }
            } else {
                ui.loginVerification(false);
                sc.nextLine();
                welcome();
                break;
            }
        }

        //System.out.println("Verifying...");
    }

    public void register() {
        User user;
        String password;
        String confirm_password;
        int type = -1;
        Map<String, String> usermap = new HashMap<>();
        //loop until the user input is correct
        while (true) {
            ui.registerPage();
            try {
                //if user input is not a correct number, return a warning
                type = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                continue;
            }
            switch (type) {
                case 1:
                    user = new Customer();
                    user.setUsertype("customer");
                    break;
                case 2:
                    user = new Owner();
                    user.setUsertype("owner");
                    break;
                default:
                    //if user input an incorrect number, return a warning
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Please select a correct user type!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
            }
            break;
        }

        while (true) {
            ui.registerEnter("username");
            String username = sc.nextLine();
            if (username.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("User name can not be blank!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setUsername(username);
            break;
        }


        while (true) {
            ui.registerEnter("password");
            password = sc.nextLine();
            if (password.equals(""))
                continue;
            if (password.length() < 6 || password.length() > 15) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the password between 6 and 15 characters!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }

            ui.registerEnter("password again");
            confirm_password = sc.nextLine();
            if (confirm_password.equals(""))
                continue;
            if (!confirm_password.equals(password)) {
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

        while (true) {
            ui.registerEnter("first name");
            String fname = sc.nextLine();
            if (fname.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the first name correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setFname(fname);
            break;
        }

        while (true) {
            ui.registerEnter("last name");
            String lname = sc.nextLine();
            if (lname.equals("")) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the last name correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setLname(lname);
            break;
        }

        while (true) {
            ui.registerEnter("email");
            String email = sc.nextLine();
            if (!email.contains("@") || email.length() <= 3) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please enter the email correctly!!!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            user.setEmail(email);
            break;
        }

        if (type == 1 || type == 2) {
            while (true) {
                ui.registerEnter("address");
                String address = sc.nextLine();
                if (address.equals("")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Address should not be blank");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
                user.setAddress(address);
                break;
            }

            while (true) {
                ui.registerEnter("phone no");
                String phone_no = sc.nextLine();
                if (phone_no.equals("")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Phone number should not be blank!!!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
                user.setPhone_no(phone_no);
                break;
            }

            while (true) {
                ui.registerEnter("date of birth(\"dd-MM-yyyy\")");
                String userInput = sc.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                try {
                    Date userInputDate = sdf.parse(userInput);
                    if (userInputDate.compareTo(new Date()) >= 0 )
                    {
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("Are you kidding me??");
                        ui.displayInfo("You should input the right birthday");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        continue;
                    }
                    else
                        user.setDob(userInput);
                    break;
                } catch (Exception e) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Please input the right format of birthday!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    continue;
                }
            }

            if (type == 1) {//if customer, set discount by choosing type
                while (true) {
                    ui.registerEnter("discount type");
                    ui.displayInfo("Input 0 as default!");
                    Discount discount = new Discount();
                    Map<String, Double> map = new HashMap<>(discount.viewAllDiscount());
                    Set<String> set = map.keySet();
                    for (String key : set) {
                        ui.displayInfo(key + ": " + map.get(key));
                    }
                    String userInput = sc.nextLine();
                    if (userInput.equals("0"))
                        break;
                    if (set.contains(userInput)) {
                        user.setDiscount(String.valueOf(map.get(userInput)));
                        break;
                    } else {
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("Please check your input, or input 0 to set as default!");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    }
                }
            }

        }

        //use a HashMap to store user's information
        usermap.put("usertype", user.getUsertype());
        usermap.put("username", user.getUsername());
        usermap.put("password", user.getPassword_hash());
        usermap.put("fname", user.getFname());
        usermap.put("lname", user.getLname());
        usermap.put("email", user.getEmail());
        usermap.put("address", user.getAddress());
        usermap.put("phone_no", user.getPhone_no());
        usermap.put("dob", user.getDob());
        usermap.put("id", String.valueOf(fileIO.getBiggestUserID() + 1));
        usermap.put("discount", user.getDiscount());
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
        ui.displayInfo("Dob: " + usermap.get("dob"));
        if (user.getUsertype().equals("customer"))
            ui.displayInfo("Disocunt: " + usermap.get("discount"));
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");

        //write the map into a file
        fileIO.register(usermap);
        //now turn to the login page
        ui.displayInfo("What do you want to do now?:");
        ui.displayInfo("1. Back to home screen");
        ui.displayInfo("2. Press any other key to exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String operation = sc.nextLine();
        if (operation.equals("1")) {
            new Main().welcome();
        } else {
            System.exit(0);
        }
    }

    /**
     * These are the operations for customer main
     * This is the welcome page for customer
     */
    private void CustomerWelcome() {
        fileIO.startup();
        ui.customerMainMenu(user.getUsername());
        String select = "";
        select = sc.nextLine();
        switch (select) {
            case "1"://view hall lists
                viewHallList();
                break;
            case "2"://search a hall
                searchAHall();
                break;
            case "3"://view the quotation
                viewCustomerQuotation(user);
                break;
            case "4"://manage bookings
                viewCustomerBookingList(user);
                break;
            case "5"://manage account
                break;
            case "Q"://quit
                new Main().welcome();
            default:
                CustomerWelcome();
                break;
        }
    }

    private void viewCustomerBookingList(User user) {
        ArrayList<Booking> bookings = new ArrayList<Booking>(fileIO.viewCustomerBookingList(user));
        if (bookings.size() == 0) {
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Sorry, you don't have any booking history!");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            CustomerWelcome();
        }
        ui.manageBookings(bookings);
        while (true) {
            ui.displayInfo("Please select the booking you want!");
            String userInput = sc.nextLine();
            if (userInput.equals("")) {
                //ui.error
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Sorry, you have to input the correct number of booking");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            } else if (userInput.equals("R"))
                CustomerWelcome();
            else
                try {
                    int bookingSelection = Integer.parseInt(userInput);
                    ui.displayBooking(bookings.get(bookingSelection - 1));
                    customerOperateBooking(bookings.get(bookingSelection - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                    CustomerWelcome();
                }
                break;
        }

    }

    private void customerOperateBooking(Booking booking) {
        ui.displayBookingOperation();
        String userInput = "";
        int selection = 0;
        try {
            userInput = sc.nextLine();
            selection = Integer.parseInt(userInput);
        } catch (Exception e) {
            if (userInput.equals("R"))
                viewCustomerBookingList(user);
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Please input the correct content!");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            customerOperateBooking(booking);
        }
        if (selection == 1) {
            double minus = 0.0;
            long nowTime = new Date().getTime();
            long bookingTime = booking.getStartTime().getTime();
            minus = bookingTime - nowTime / (1000 * 3600);
            if (!booking.getState().equals("new")){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Sorry! The booking has been changed previously!");
                ui.displayInfo("Please select the booking with state \"new\"");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                viewCustomerBookingList(user);
            }
            if (minus >= 24)
                customerChangeBookingDate(booking);
            else {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Sorry, the start time is within 24 hours!");
                ui.displayInfo("The booking date can not be within 24 hours!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                customerOperateBooking(booking);
            }
        }
    }

    private void customerChangeBookingDate(Booking booking) {
        try {
            Date date;
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String userInput;
            while (true) {
                ui.displayInfo("Please input the date! (dd-MM-yyyy)");
                sb = new StringBuilder();
                sdf = new SimpleDateFormat("dd-MM-yyyy");
                userInput = sc.nextLine();
                try {
                    date = sdf.parse(userInput);
                    //how to check whether the date is correct?
                    if ((date.getTime() - new Date().getTime()) <= 0) {
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("You should input a future date!!");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        sc.nextLine();
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Wrong date format! Please input as dd-MM-yyyy");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    continue;
                }
                sb.append(userInput);
                sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
                ui.displayInfo("Please select the time period!");
                ui.displayInfo("1. Morning 9:00-11:00");
                ui.displayInfo("2. Afternoon 15:00-17:00");
                ui.displayInfo("3. Evening 19:00-21:00");
                userInput = sc.nextLine();
                int selection = 0;
                try {
                    selection = Integer.parseInt(userInput);
                    Date startTime;
                    Date endTime;
                    switch (selection) {
                        case 1:
                            startTime = sdf.parse("9:00 " + sb.toString());
                            endTime = sdf.parse("11:00 " + sb.toString());
                            break;
                        case 2:
                            startTime = sdf.parse("15:00 " + sb.toString());
                            endTime = sdf.parse("17:00 " + sb.toString());
                            break;
                        case 3:
                            startTime = sdf.parse("19:00 " + sb.toString());
                            endTime = sdf.parse("21:00 " + sb.toString());
                            break;
                        default:
                            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                            ui.displayInfo("Please select the correct time.");
                            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                            sc.nextLine();
                            continue;
                    }
                    Booking newBooking = booking;
                    newBooking.setStartTime(startTime);
                    newBooking.setEndTime(endTime);
                    newBooking.setState("changed");
                    fileIO.updateBooking(newBooking);
                    ui.displayInfo("Change successfully!");
                    viewCustomerBookingList(user);
                    break;
                } catch (Exception e) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Wrong selection!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private void viewHallList(){
        ui.viewHallList(fileIO.viewAllHall(),user.getUsername());
        //user can select a hall to view
        while(true){
            String userInput = sc.nextLine();
            if (userInput.equals("")) {
                //ui.error
                continue;
            }
            else if (userInput.equals("R"))
                new Main().CustomerWelcome();
            else
                try {
                    int hallSelection = Integer.parseInt(userInput);
                    ui.displayHall(fileIO.viewAllHall().get(hallSelection-1));
                    customerOperateHall(fileIO.viewAllHall().get(hallSelection-1));
                }catch (Exception e){
                    //ui.error
                    viewHallList();
                }
        }
    }

    private void searchAHall() {
        ui.searchAHall();
        int selection = 0;
        while (true) {
            try {
                ui.displayInfo("Please select how to search hall!");
                selection = Integer.parseInt(sc.nextLine());
                switch (selection) {
                    case 0:
                        CustomerWelcome();
                        break;
                    case 1://by name
                        searchAHallByName();
                        break;
                    case 2://by event type
                        //searchAHallByEventType();
                        break;
                    case 3://by location
                        searchAHallByLocation();
                        break;
                    default:
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("Please input the correct selection.");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        sc.nextLine();
                        continue;
                }
            } catch (Exception e) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the correct selection!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            break;
        }
    }

    private void searchAHallByName() {
        ui.searchAHallBy("name");
        String hallName;
        hallName = sc.nextLine();
        Hall searchedHall = fileIO.searchAHallByName(hallName);
        if (searchedHall == null){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Not found! Please check the name again.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            searchAHall();
        }
        else
            customerOperateHall(searchedHall);
    }

    private void searchAHallByLocation() {
        ui.searchAHallBy("location");
        String location;
        location = sc.nextLine();
        Hall searchedHall = fileIO.searchAHallByLocation(location);
        if (searchedHall == null){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Not found! Please check the input!");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            searchAHall();
        }
        else
            customerOperateHall(searchedHall);

    }

    private void customerOperateHall(Hall hall) {
        ui.customerSelectHall(hall);
        int selection = 0;
        String userInput = sc.nextLine();
        while (true){
            try {
                selection = Integer.parseInt(userInput);
                switch (selection){
                    case 1:
                        System.out.println("Send a request!");
                        askForAQuotation(hall,user);
                        break;
                    case 2:
                        System.out.println("View comment of the hall!");
                        break;
                    default:
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("Please input the correct selection!");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        sc.nextLine();
                        continue;
                }
            }catch (Exception e){
                if (userInput.equals("R")) {
                    CustomerWelcome();
                }
                else {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Please input the correct selection!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    continue;
                }
            }
            //break;
            //temporary
            customerOperateHall(hall);
        }
    }

    private void askForAQuotation(Hall hall,User user) {
        Quotation quotation = new Quotation();
        quotation.setState("new");
        quotation.setOwnerId(hall.getOwnerId());
        quotation.setHallId(hall.getHallId());
        quotation.setPrice(hall.getPrice());
        quotation.setCustomerId(Integer.parseInt(user.getId()));
        //quotation.setQuotationId(fileIO.getBiggestQuotationID() + 1);
        while (true){
            ui.displayInfo("Please choose the event type!");
            String supportEventType = hall.getSupportEventType();
            String[] eventTypes = supportEventType.split("[+]");
            int index = 0;
            for (String type : eventTypes){
                ui.displayInfo(++index + ". " + type);
            }
            String selectionInput = sc.nextLine();
            try {
                int selection = Integer.parseInt(selectionInput);
                quotation.setEventType(eventTypes[selection-1]);
                break;
            } catch (Exception e){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please check your input.");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }

        }
        /**
         * User input date and select time
         */
        while (true){
            ui.displayInfo("Please input the date! (dd-MM-yyyy)");
            Date date;
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            String userInput = sc.nextLine();
            try {
                date = sdf.parse(userInput);
                if (date.compareTo(new Date()) <= 0 )
                {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("You should input a future date!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
            }catch (Exception e){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Wrong format!!!!!!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                continue;
            }
            sb.append(userInput);
            sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
            ui.displayInfo("Please select the time period!");
            ui.displayInfo("1. Morning 9:00-11:00");
            ui.displayInfo("2. Afternoon 15:00-17:00");
            ui.displayInfo("3. Evening 19:00-21:00");
            userInput = sc.nextLine();
            int selection = 0;
            try {
                selection = Integer.parseInt(userInput);
                Date startTime;
                Date endTime;
                switch (selection){
                    case 1:
                        startTime = sdf.parse("9:00 "+sb.toString());
                        endTime = sdf.parse("11:00 "+sb.toString());
                        break;
                    case 2:
                        startTime = sdf.parse("15:00 "+sb.toString());
                        endTime = sdf.parse("17:00 "+sb.toString());
                        break;
                    case 3:
                        startTime = sdf.parse("19:00 "+sb.toString());
                        endTime = sdf.parse("21:00 "+sb.toString());
                        break;
                    default:
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("Please select the correct option.");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        sc.nextLine();
                        continue;
                }
                quotation.setStartTime(startTime);
                quotation.setEndTime(endTime);
            }catch (Exception e)
            {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please select the correct option.");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            break;
        }

        while (true){
            ui.displayInfo("Please input the event size!");
            String userinput = sc.nextLine();
            int size;
            try {
                size = Integer.parseInt(userinput);
                if (size > 0 && size < 10000)
                    quotation.setEventSize(size);
                else {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Size should be larger than 0 and less than 10000.");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    continue;
                }
            }catch (Exception e) {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the correct integer for the size.");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            break;
        }

        while (true){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Please choose whether catering!");
            ui.displayInfo("Input Y to means your event needs catering from host.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            String userInput = sc.nextLine().toUpperCase();
            if (userInput.equals("Y"))
                quotation.setWhetherCatering(true);
            else
                quotation.setWhetherCatering(false);
            break;
        }
        quotation.setPrice((int) hall.getPrice() * (1-hall.getDiscount()) * (1-Double.parseDouble(user.getDiscount())));
        ui.displayQuotation(quotation);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        ui.displayInfo("Please confirm send the request!");
        ui.displayInfo("Input Y to send or any other input to leave! ");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String userInput = sc.nextLine().toUpperCase();
        if (userInput.equals("Y")) {
            fileIO.askForAQuotation(quotation);
            ui.displayInfo("Sent successfully!");
            sc.nextLine();
            CustomerWelcome();
        }
        else
            customerOperateHall(hall);
    }

    private void viewCustomerQuotation(User user){
        ArrayList<Quotation> quotations = fileIO.readCustomerQuotationList(user);
        if (quotations.size() == 0){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("You don't have any quotations right now.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            CustomerWelcome();
        }
        else {
            ui.viewQuotations(quotations);
            ui.displayInfo("Please choose a quotation to operate.");
            ui.displayInfo("Input the quotation ID...");
            ui.displayInfo("Input R to exit!");
            String userInput = sc.nextLine();
            int selection = 0;
            try {
                selection = Integer.parseInt(userInput);
            }catch (Exception e){
                if (userInput.toUpperCase().equals("R"))
                    CustomerWelcome();
                else
                    viewCustomerQuotation(user);
            }
            if (selection>0 && selection<=quotations.size()){
                Quotation quotation = quotations.get(selection-1);
                ui.displayQuotation(quotation);
                ui.customerOperateAQutation();
                customerOperateQuotation(quotation);
            }
            else {
                ui.displayInfo("Please input the right quotation ID");
                viewCustomerQuotation(user);
            }
        }
    }

    private void customerOperateQuotation(Quotation quotation) {
        String userInput = "";
        userInput = sc.nextLine();
        switch (userInput){
            case "1":
                if (quotation.getState().equals("accepted"))
                    booking(quotation);
                else if (quotation.getState().equals("new")){
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Sorry, please wait for owner to process the request!");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    viewCustomerQuotation(user);
                }
                else if (quotation.getState().equals("rejected")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Sorry, the request has been declined by the owner.");
                    ui.displayInfo(" Please make a new quotation.");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    viewCustomerQuotation(user);
                }
                else if (quotation.getState().equals("declined")) {
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    ui.displayInfo("Sorry! The quotation has been declined by you.");
                    System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                    sc.nextLine();
                    viewCustomerQuotation(user);
                }
                break;
            case "2":
                //fileIO.declineQuotation(quotation);
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("This quotation has been declined!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                break;
            case "R":
                viewCustomerQuotation(user);
                break;
            default:
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right option!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                customerOperateQuotation(quotation);
                break;

        }
    }

    private void booking(Quotation quotation) {
        Booking booking = new Booking(quotation);
        ui.displayBooking(booking);
        ui.displayInfo("Now pay a deposit!");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        ui.displayInfo("Please input your credit card information");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        ui.displayInfo("Input \"R\" to go back!");
        String userInput = sc.nextLine();
        if (userInput.equals("")){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Please input in right format.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            booking(quotation);
        }
        if (userInput.equals("R")){
            booking(quotation);
        }
        ui.displayInfo("This is your payment info!");
        ui.displayInfo(userInput);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        ui.displayInfo("Please input \"Y\" to pay a deposit or \"N\" to decline");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        userInput = sc.nextLine();
        if (userInput.toUpperCase().equals("Y")){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("You have pay the deposit successfully!");
            ui.displayInfo("Now you can check or manage your booking in Main menu");
            ui.displayInfo("You can also check your receipt in Main menu!");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            fileIO.makeABooking(booking);
            Payment payment = new Payment(booking);
            fileIO.payADeposit(payment);
            ui.displayInfo("Please press enter to back to the Main menu");
            sc.nextLine();
            CustomerWelcome();
        }
        else
            customerOperateQuotation(quotation);
    }

    /**
     * These are the operations for owner
     * This is the welcome page of owner
     */
    private void OwnerWelcome(){
        ui.ownerMainMenu(user.getUsername());
        fileIO.startup();
        String select = "";
        select = sc.nextLine();
        switch (select){
            case "1"://create a hall
                createHall();
                break;
            case "2"://manage halls
                manageAHall();
                break;
            case "3"://view request
                viewOwnerQuotationList(user);
                break;
            case "4"://manage bookings
                //manage bookings includes cancel bookings and refund payment
                break;
            case "5"://manage account
                break;
            case "6": //manage payment
                break;
            case "Q"://quit
                new Main().welcome();
            default:
                new Main().OwnerWelcome();
                break;
        }
    }

    private void viewOwnerQuotationList(User user) {
        ArrayList<Quotation> quotations = new ArrayList<>(fileIO.readOwnerQuotationList(user));
        if (quotations.size() == 0){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("You don't have any request right now.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            OwnerWelcome();
        }
        else {
            ui.viewQuotations(quotations);
            ui.displayInfo("Please choose a request to operate...");
            String userInput = sc.nextLine();
            int selection = 0;
            try {
                selection = Integer.parseInt(userInput);
            }catch (Exception e){
                if (userInput.toUpperCase().equals("R"))
                    OwnerWelcome();
                else
                    viewOwnerQuotationList(user);
            }
            if (selection>0 && selection<=quotations.size()){
                Quotation quotation = quotations.get(selection-1);
                ui.displayQuotation(quotation);
                ui.ownerOperateAQuotation();
                ownerOperateQuotation(quotation);
                if (quotation.getState().equals("new")){
                    //////
                }

            }
            else {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please choose the right option!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                viewOwnerQuotationList(user);
            }
        }


    }

    private void ownerOperateQuotation(Quotation quotation) {
        String userInput = "";
        userInput = sc.nextLine();
        switch (userInput){
            case "1":
                if (quotation.getState().equals("accepted")) {
                    ui.displayInfo("The quotation has already been sent to the customer.");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                if (quotation.getState().equals("new")){
                    ui.displayInfo("Now the quotation is sent to the customer!");
                    fileIO.ownerSendQuotation(quotation);
                    fileIO.startup();
                    ui.displayInfo("Please enter to return!");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                if (quotation.getState().equals("rejected")) {
                    ui.displayInfo("The request has been rejected by you!");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                if (quotation.getState().equals("declined")) {
                    ui.displayInfo("The quotation has been declined by customer!");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                break;
            case "2":
                if (quotation.getState().equals("accepted")) {
                    ui.displayInfo("The quotation has already been sent to the customer.");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                if (quotation.getState().equals("new")){
                    ui.displayInfo("Now the quotation has been accepted!");
                    fileIO.ownerRejectRequest(quotation);
                    viewOwnerQuotationList(user);
                }
                if (quotation.getState().equals("rejected")) {
                    ui.displayInfo("The request has already been rejected by you!");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                if (quotation.getState().equals("declined")) {
                    ui.displayInfo("The request has been declined by customer!");
                    sc.nextLine();
                    viewOwnerQuotationList(user);
                }
                break;
            case "R":
                viewCustomerQuotation(user);
                break;
            default:
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right option!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                customerOperateQuotation(quotation);
                break;
        }
    }


    private static void createHall(){
        String name;
        String location;
        String supportEventType;
        double hallDiscount = 0.00;
        String picture;
        String ownerId = user.getId();
        String description;
        double price;
        Map<String,String> hallMap = new HashMap<>();
        //loop until the user input is correct

        while(true) {
            ui.createAHall("Hall Name");
            name = sc.nextLine();
            if (name.equals(""))
                continue;
            break;
        }

        while(true) {
            ui.createAHallType();
            String typeSelectRaw;
            StringBuilder sb = new StringBuilder();
            typeSelectRaw = sc.nextLine();
            if (typeSelectRaw.equals("") || typeSelectRaw.equals(","))
                continue;
            if (!typeSelectRaw.contains(",") && typeSelectRaw.length() == 1){
                switch (typeSelectRaw){
                    case "1":
                        sb.append("Wedding Ceremony");
                        break;
                    case "2":
                        sb.append("Wedding Reception");
                        break;
                    case "3":
                        sb.append("Birthday");
                        break;
                    case "4":
                        sb.append("Anniversary");
                        break;
                    default:
                        continue;
                }
                supportEventType = sb.toString();
                break;
            }
            String[] typeSelect = typeSelectRaw.split(",");
            int i=0;
            for(; i<typeSelect.length-1; i++){
                switch (typeSelect[i]){
                    case "1":
                        sb.append("Wedding Ceremony + ");
                        break;
                    case "2":
                        sb.append("Wedding Reception + ");
                        break;
                    case "3":
                        sb.append("Birthday + ");
                        break;
                    case "4":
                        sb.append("Anniversary + ");
                        break;
                    default:
                        sb.append("");
                        break;
                }
            }
            switch (typeSelect[i]){
                case "1":
                    sb.append("Wedding Ceremony");
                    break;
                case "2":
                    sb.append("Wedding Reception");
                    break;
                case "3":
                    sb.append("Birthday");
                    break;
                case "4":
                    sb.append("Anniversary");
                    break;
                default:
                    break;
            }
            supportEventType = sb.toString();
            if (supportEventType.equals(""))
                continue;
            break;
        }

        while(true) {
            ui.createAHall("Hall Location");
            location = sc.nextLine();
            if (location.equals(""))
                continue;
            break;
        }


        while(true) {
            ui.createAHall("Picture");
            picture = sc.nextLine();
            if (picture.equals(""))
                continue;
            break;
        }

        while(true) {
            ui.createAHall("Discount");
            try {
                hallDiscount = Double.parseDouble(sc.nextLine());
                if (hallDiscount < 0 || hallDiscount > 1){
                    ui.displayInfo("Discount should between 0 and 1!");
                    continue;
                }
            }catch (Exception e){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right discount!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            break;
        }

        while (true){
            ui.createAHall("Description");
            String rawDescription = sc.nextLine();
            if (rawDescription.equals(""))
                continue;
            description =  rawDescription.replace(",","*");
            break;
        }

        while(true) {
            ui.createAHall("Standard Price");
            try {
                price = sc.nextDouble();
            } catch (Exception e)
            {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right price!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            if (price < 0)
                continue;
            break;
        }

        //use a HashMap to store user's information
        fileIO.startup();
        hallMap.put("hallId",String.valueOf((new FileIO().getBiggestHallID()+1)));
        hallMap.put("ownerId",String.valueOf(ownerId));
        hallMap.put("name",name);
        hallMap.put("location",location);
        hallMap.put("supportEventType",supportEventType);
        hallMap.put("discount",String.valueOf(hallDiscount));
        hallMap.put("picture",picture);
        hallMap.put("description",description);
        hallMap.put("price",String.valueOf(price));






        //display the information after input
        ui.createHallSuccess();
        ui.displayCreateSuccessful(hallMap);

        //write the map into a file
        fileIO.createAHall(hallMap);
        //now turn to the login page
        ui.displayInfo("What do you want to do now?:");
        ui.displayInfo("1. Back to Owner Main Screen");
        ui.displayInfo("All other input will exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String operation = sc.nextLine();
        if (operation.equals("1"))
            new Main().OwnerWelcome();
        else
            System.exit(0);
    }

    public ArrayList<Hall> viewOwnHall(int id){
        return fileIO.viewOwnHall(id);
    }

    public Hall viewAHall(int hallId){
        return fileIO.viewAHall(hallId);
    }

    public void manageAHall(){
        ArrayList<Hall> ownHall = new ArrayList<>(viewOwnHall(Integer.parseInt(user.getId())));
        if (ownHall.size() == 0) {
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("You have no halls registered in the system!");
            ui.displayInfo("You can create your first hall or back to the main menu!");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            new Main().OwnerWelcome();
            return;
        }
        ui.manageHalls(ownHall);
        //read user select
        while (true) {
            String input = "";
            int hallID = 0;
            try {
                input = sc.nextLine();
                hallID = Integer.parseInt(input);
            } catch (Exception e) {
                switch (input.toUpperCase()){
                    case "R":
                        new Main().OwnerWelcome();
                    default:
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        ui.displayInfo("Please input the right ID!");
                        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                        sc.nextLine();
                        continue;
                }

            }
            int maximumId = 0;
            for (Hall demo : ownHall){
                if (demo.getHallId() > maximumId)
                    maximumId = demo.getHallId();
            }
            if (hallID == 0 || hallID > maximumId){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right ID!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            else {
                hall = viewAHall(hallID);
                ui.manageSelectedHall(hall);
                break;
            }
        }
        ui.displayInfo("Please select the option...");
        int select = 0;
        while (true){
            try {
                select = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right option!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                continue;
            }
            switch (select){
                case 1:
                    updateHall(hall);
                    break;
                case 2:
                    updateDiscounts(hall);
                    break;
                default:
                    continue;
            }

        }



    }

    private void updateDiscounts(Hall hall) {
        ui.updateDiscounts(hall);
        String input = "";
        double discount = 0.00;
        try {
            input = sc.nextLine();
            discount = Double.parseDouble(input);
        }catch (Exception e){
            if (input.toUpperCase().equals("R"))
                manageAHall();
            else {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right discount!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                updateHall(hall);
            }
        }
        if (discount < 0.00 || discount > 1.00){
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            ui.displayInfo("Please input discount between (0.00 - 1.00)!");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            sc.nextLine();
            manageAHall();
        }
        fileIO.updateDiscount(hall,discount);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        ui.displayInfo("Update discount successfully!");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        sc.nextLine();
        manageAHall();
    }

    private void updateHall(Hall hall) {
        ui.updateHall(hall);
        String input = "";
        int select = 0;
        try {
            input = sc.nextLine();
            select = Integer.parseInt(input);
        } catch (Exception e) {
            if (input.toUpperCase().equals("R"))
                manageAHall();
            else {
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                ui.displayInfo("Please input the right option!");
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
                sc.nextLine();
                updateHall(hall);
            }
        }
        switch (select) {
            case 1:
                break;
            case 2:
                break;
            default:
                updateHall(hall);
                break;
        }
    }

    /**
     * These are the operations for administrator
     */
    public void AdminWelcome(){
        ui.adminMainMenu(user.getUsername());
        String select = "";
        select = sc.nextLine();
        switch (select){
            case "1"://create a hall
                break;
            case "2"://manage halls
                break;
            case "3"://view request
                break;
            case "4"://manage bookings
                //manage bookings includes cancel bookings and refund payment
                break;
            case "5"://manage account
                break;
            case "6": //manage payment
                break;
            case "Q"://quit
                new Main().welcome();
            default:
                new Main().AdminWelcome();
                break;
        }
    }
}

