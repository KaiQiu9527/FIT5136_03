
import java.util.Scanner;
/**
 * Display the design of basic UI.
 *
 * @author team 3
 * @version 1.0
 */
public class UI
{
    public void loginPage()
    {
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        text.simpleTitle("Welecome to the Prime Events");
        text.displayInfo("   Select your choice:");
        text.displayInfo("1. Log in");
        text.displayInfo("2. New user sign up");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        int input = console.nextInt();
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
    }
    
    public void register()
    {
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        text.simpleTitle("Register");
        text.displayInfo("Please select your user type:");
        text.displayInfo("1. Customer");
        text.displayInfo("2. Owner");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        int type = console.nextInt();
        System.out.print('\u000C');
        text.simpleTitle("Register");
        text.displayInfo("Please enter your username:");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String username = console.nextLine();
        System.out.print('\u000C');
        text.simpleTitle("Register");
        text.displayInfo("Please enter your password:");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String password = console.nextLine();
        System.out.print('\u000C');
        text.simpleTitle("Register");
        text.displayInfo("Please enter your password again:");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        String check = console.nextLine();
        System.out.print('\u000C');
        // Display the message for succesfully sign up
    }
        
    public void login()
    {
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        text.simpleTitle("Welecome to the Prime Events");
        text.displayInfo("Login:");
        text.displayInfo("Please enter username:");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        console.nextLine();
        System.out.print('\u000C');
        text.simpleTitle("Welecome to the Prime Events");
        text.displayInfo("Login:");
        text.displayInfo("Please enter password:");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        console.nextLine();
        System.out.print('\u000C');
        System.out.println("Verifying...");
    }
    
    public void customerMainMenu()
    {
        Text text = new Text();
        text.title("Main Menu", "Customer", "username");
        text.displayInfo("   Enter the choice:");
        text.displayInfo("1. View the halls");
        text.displayInfo("2. Manage bookings");
        text.displayInfo("3. Manage account");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        text.displayInfo("Q. Log out");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
    
    public void viewHalls()
    {
        Text text = new Text();
        text.title("View the halls", "Customer", "username");
        text.displayHall();
        text.displayInfo("   Enter the choice:");
        text.displayInfo("1. Select this hall");
        text.displayInfo("2. Next page");
        text.displayInfo("3. Previous page");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        text.displayInfo("R. Return to main menu");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
    
    public void manageBookings()
    {
        Text text = new Text();
        text.title("Manage bookings", "Customer", "username");
        text.displayInfo("   Enter the choice:");
        text.displayInfo("1. Sample booking 1");
        text.displayInfo("2. Sample booking 2");
        text.displayInfo("3. Sample booking 3");
        text.displayInfo("4. Next page");
        text.displayInfo("5. Previous page");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        text.displayInfo("R. Return to main menu");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
    
    public void manageAccount()
    {
        Text text = new Text();
        text.title("Manage account", "Customer", "username");
        text.displayInfo("   Enter the choice:");
        text.displayInfo("1. Change password");
        text.displayInfo("2. Change personal details");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        text.displayInfo("R. Return to main menu");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
    
    public void displayUI()
    {
        Scanner console = new Scanner(System.in);
        loginPage();
        console.nextLine();
        System.out.print('\u000C');
        customerMainMenu();
        console.nextLine();
        System.out.print('\u000C');
        viewHalls();
        console.nextLine();
        System.out.print('\u000C');
        manageBookings();
        console.nextLine();
        System.out.print('\u000C');
        manageAccount();
    }
}
