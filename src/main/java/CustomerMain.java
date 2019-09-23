import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMain {
    static UI ui = new UI();
    private static User user;
    static Scanner sc = new Scanner(System.in);
    static FileIO fileIO = new FileIO();
    private static ArrayList<Hall> halls;

    public CustomerMain(User user)
    {
        this.user = user;
    }

    public static void welcome(){
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
                break;
            case "3"://view the quotation
                break;
            case "4"://manage bookings
                break;
            case "5"://manage account
                break;
            case "Q"://quit
                System.exit(0);
            default:
                welcome();
                break;
        }
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
                    ui.displayHall(halls.get(hallSelection-1));
                }catch (Exception e){
                    //ui.error
                    continue;
                }
                continue;
        }
    }
}
