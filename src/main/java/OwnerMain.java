import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OwnerMain {
    static UI ui = new UI();
    private static User user;
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<Hall> halls;
    static Text text = new Text();
    static FileIO fileIO = new FileIO();

    public OwnerMain(User user)
    {
        this.user = user;
    }

    public static void welcome(){
        ui.ownerMainMenu(user.getUsername());
        String select = "";
        select = sc.nextLine();
        switch (select){
            case "1"://create a hall
                createHall();
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
                welcome();
                break;
        }
    }

    public static void createHall(){
        Scanner console = new Scanner(System.in);
        String name;
        String location;
        String supportEventType;
        double hallDiscount = 1.00;
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

    public static void viewHallList(){
        ui.viewHallList(halls,user.getUsername());
        while(true){
            String userInput = sc.nextLine();
            if (userInput.equals("")) {
                //ui.error
                continue;
            }
            else if (userInput.equals("R"))
                welcome();
            else
                try {
                    int hallSelection = Integer.parseInt(userInput);
                    ui.displayHall(halls.get(hallSelection));
                }catch (Exception e){
                    //ui.error
                    continue;
                }
        }
    }
}
