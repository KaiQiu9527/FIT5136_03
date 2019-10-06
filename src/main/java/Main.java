import jdk.jshell.spi.ExecutionControlProvider;

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
    static private ArrayList<Hall> halls = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.welcome();
    }

    /**
     *This is the welcome page, all the operations start from here
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
                    CustomerWelcome();
                    break;
                }
                else if (usertype.equals("owner")) {
                    OwnerWelcome();
                    //
                    break;
                }
                if (usertype.equals("admin")) {
                    AdminWelcome();
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

            while (true){
                ui.registerEnter("date of birth(\"dd-MM-yyyy\")");
                String userInput = sc.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try{
                    Date userInputDate = sdf.parse(userInput);
                    if (userInputDate.after(sdf.getCalendar().getTime())){
                        ui.displayInfo("Birthday should be after today!");
                        continue;
                    }
                    else {
                        user.setDob(userInput);
                        break;
                    }
                }catch (Exception e){
                    ui.displayInfo("Please input the right format of birthday!");
                    continue;
                }
            }

            if (type == 1){//if customer, set discount by choosing type
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
                    }
                    else {
                        ui.displayInfo("Please check your input, or input 0 to set as default!");
                    }
                }
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
        usermap.put("dob",user.getDob());
        usermap.put("id", String.valueOf(fileIO.getBiggestUserID()+1));
        usermap.put("discount",user.getDiscount());
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
            ui.displayInfo("Disocunt: "+ usermap.get("discount"));
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
            new Main().welcome();
        }
        else {
            System.exit(0);
        }
    }
    /**
     *These are the operations for customer main
     *This is the welcome page for customer
     */
    private void CustomerWelcome(){
        fileIO.startup();
        halls = fileIO.getHalls();
        ui.customerMainMenu(user.getUsername());
        String select = "";
        select = sc.nextLine();
        switch (select){
            case "1"://view hall lists
                viewHallList();
                break;
            case "2"://search a hall
                searchAHall();
                break;
            case "3"://view the quotation
                break;
            case "4"://manage bookings
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

    private void viewHallList(){
        ui.viewHallList(halls,user.getUsername());
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
                    ui.displayHall(halls.get(hallSelection-1));
                    customerOperateHall(halls.get(hallSelection-1));
                }catch (Exception e){
                    //ui.error
                    viewHallList();
                }
        }
    }

    private void searchAHall(){
        ui.searchAHall();
        int selection = 0;
        while (true) {
            try {
                selection = Integer.parseInt(sc.nextLine());
                switch (selection){
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
                        //searchAHallByLocation();
                        break;
                    default:
                        continue;
                }
            } catch (Exception e) {
                ui.displayInfo("Please input the right selection!");
                continue;
            }
        }
    }

    private void searchAHallByName() {
        ui.searchAHallBy("name");
        String hallName;
        hallName = sc.nextLine();
        boolean found = false;
        Hall chosenHall = new Hall();
        for (Hall hall : halls){
            if (hall.getName().equals(hallName)) {
                chosenHall = hall;
                found = true;
            }
        }
        if (!found){
            ui.displayInfo("Not found! Please check the input!");
            searchAHall();
        }
        else
            customerOperateHall(chosenHall);


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
                        break;
                    case 2:
                        System.out.println("View comment of the hall!");
                        break;
                    default:
                        continue;
                }
            }catch (Exception e){
                if (userInput.equals("R")) {
                    CustomerWelcome();
                }
                else {
                    ui.displayInfo("Please input the right option!");
                    continue;
                }
            }
            //break;
            //temporary
            customerOperateHall(hall);
        }
    }

    /**
     * These are the operations for owner
     * This is the welcome page of owner
     */
    private void OwnerWelcome(){
        ui.ownerMainMenu(user.getUsername());
        fileIO.startup();
        //fill the halls
        halls = new ArrayList<>(fileIO.halls);
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

    private static void createHall(){
        Scanner console = new Scanner(System.in);
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
            name = console.nextLine();
            if (name.equals(""))
                continue;
            break;
        }

        while(true) {
            ui.createAHallType();
            String typeSelectRaw;
            typeSelectRaw = sc.nextLine();
            String[] typeSelect = typeSelectRaw.split(",");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<typeSelect.length; i++){
                switch (typeSelect[i]){
                    case "1":
                        sb.append("Wedding Ceremony ");
                        break;
                    case "2":
                        sb.append("Wedding Reception ");
                        break;
                    case "3":
                        sb.append("Birthday ");
                        break;
                    case "4":
                        sb.append("Anniversary ");
                        break;
                    default:
                        sb.append("");
                        break;
                }
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
                if (location.equals(""))
                    continue;
            }catch (Exception e){
                ui.displayInfo("Please input the right discount!");
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
            ui.createAHall("Price");
            try {
                price = sc.nextDouble();
            } catch (Exception e)
            {
                ui.displayInfo("Please input the right price!");
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
        String operation = console.nextLine();
        if (operation.equals("1"))
            new Main().OwnerWelcome();
        else
            System.exit(0);
    }

    public static ArrayList<Hall> viewOwnHall(int id){
        ArrayList<Hall> ownHalls = new ArrayList<>();
        for (Hall hall : halls){
            if (hall.getOwnerId() == id)
                ownHalls.add(hall);
        }
        return ownHalls;
    }

    public static Hall viewAHall(int hallId){
        for (Hall hall :halls){
            if (hall.getHallId() == hallId)
                return hall;
        }
        return null;
    }

    public static void manageAHall(){
        ArrayList<Hall> ownHall = new ArrayList<>();
        ownHall = new ArrayList<>(viewOwnHall(Integer.parseInt(user.getId()))) ;
        Hall hall = new Hall();
        if (ownHall.size() == 0) {
            ui.displayInfo("You have no halls recorded in the system!");
            ui.displayInfo("You can create your first hall or back to the main menu!");
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
                        ui.displayInfo("Please input the right ID!");
                        continue;
                }

            }
            int maximumId = 0;
            for (Hall demo : ownHall){
                if (demo.getHallId() > maximumId)
                    maximumId = demo.getHallId();
            }
            if (hallID == 0 || hallID > maximumId){
                ui.displayInfo("Please input the right ID!");
                continue;
            }
            else {
                hall = viewAHall(hallID);
                ui.manageSelectedHall(hall);
                break;
            }
        }
        ui.displayInfo("Please select the option!");
        int select = 0;
        while (true){
            try {
                select = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                ui.displayInfo("Please input the right option!");
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

    private static void updateDiscounts(Hall hall) {
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
                ui.displayInfo("Please input the right discount!");
                updateHall(hall);
            }
        }
        if (discount < 0.00 || discount > 1.00){
            ui.displayInfo("Please input discount between (0.00 - 1.00)!");
            manageAHall();
        }
        halls.remove(hall);
        hall.setDiscount(discount);
        halls.add(hall);
        ArrayList<Map<String,String>> maps = new ArrayList<>();
        for (Hall aHall : halls){
            Map<String,String> hallMap = new HashMap<>();
            hallMap.put("hallId",String.valueOf((aHall.getHallId())));
            hallMap.put("ownerId",String.valueOf(aHall.getOwnerId()));
            hallMap.put("name",aHall.getName());
            hallMap.put("location",aHall.getLocation());
            hallMap.put("supportEventType",aHall.getSupportEventType());
            hallMap.put("discount",String.valueOf(aHall.getDiscount()));
            hallMap.put("picture",aHall.getPicture());
            maps.add(hallMap);
        }
        FileIO fileIO = new FileIO();
        fileIO.startup();
        fileIO.updateHallList(maps);
        manageAHall();
    }

    private static void updateHall(Hall hall) {
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
                ui.displayInfo("Please input the right option!");
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

