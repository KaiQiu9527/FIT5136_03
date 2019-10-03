import java.util.Scanner;

public class AdminMain {
    static User user;
    static UI ui = new UI();
    static Scanner sc = new Scanner(System.in);

    public AdminMain(User user){
        this.user = user;
    }

    public static void welcome(){
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
                welcome();
                break;
        }
    }

}
