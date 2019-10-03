import javax.crypto.AEADBadTagException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OwnerMain {
    static UI ui = new UI();
    private static User user;
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<Hall> halls = new ArrayList<>();
    static Text text = new Text();
    static FileIO fileIO = new FileIO();

    public OwnerMain(User user)
    {
        this.user = user;
    }

    public static void welcome(){
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
                welcome();
                break;
        }
    }

    public static void createHall(){
        Scanner console = new Scanner(System.in);
        String name;
        String location;
        String supportEventType;
        double hallDiscount = 0.00;
        String picture;
        String ownerId = user.getId();
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

        //use a HashMap to store user's information
        hallMap.put("hallId",String.valueOf((new FileIO().getHallAmount())+1));
        hallMap.put("ownerId",String.valueOf(ownerId));
        hallMap.put("name",name);
        hallMap.put("location",location);
        hallMap.put("supportEventType",supportEventType);
        hallMap.put("discount",String.valueOf(hallDiscount));
        hallMap.put("picture",picture);



        //display the information after input
        ui.createHallSuccess();
        ui.displayCreateSuccessful(hallMap);

        //write the map into a file
        fileIO.createAHall(hallMap);
        //now turn to the login page
        text.displayInfo("What do you want to do now?:");
        text.displayInfo("1. Back to Owner Main Screen");
        text.displayInfo("All other input will exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String operation = console.nextLine();
        if (operation.equals("1"))
            welcome();
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
            welcome();
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
                    case "P":
                        welcome();
                    case "R":
                        welcome();
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
        }catch (Exception e){
            if (input.toUpperCase().equals("R"))
                manageAHall();
            else {
                ui.displayInfo("Please input the right option!");
                updateHall(hall);
            }
        }
        switch (select){
            case 1:
                break;
            case 2:
                break;
            default:
                updateHall(hall);
                break;
        }

    }

//    public static void viewHallList(){
//        ui.viewHallList(halls,user.getUsername());
//        while(true){
//            String userInput = sc.nextLine();
//            if (userInput.equals("")) {
//                //ui.error
//                continue;
//            }
//            else if (userInput.equals("R"))
//                welcome();
//            else
//                try {
//                    int hallSelection = Integer.parseInt(userInput);
//                    ui.displayHall(halls.get(hallSelection));
//                }catch (Exception e){
//                    //ui.error
//                    continue;
//                }
//        }
//    }
}
