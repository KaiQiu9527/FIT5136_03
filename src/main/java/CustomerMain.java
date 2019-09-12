import java.util.Scanner;

public class CustomerMain {
    static UI ui = new UI();
    static User user;
    static Scanner sc = new Scanner(System.in);

    public CustomerMain(User user)
    {
        this.user = user;
    }

    public static void welcome(){
        ui.customerMainMenu(user.getUsername());
        String select = "";
        select = sc.nextLine();
        switch (select){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "Q":
                System.exit(0);
            default:
                welcome();
                break;
        }
    }
}
