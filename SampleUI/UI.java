import java.util.Scanner;
/**
 * Display the design of basic UI.
 *
 * @author team 3
 * @version 1.0
 */
public class UI
{
    public void login()
    {
        Scanner console = new Scanner(System.in);
        Text text = new Text();
        text.title("Welecome to the Prime Events", "Customer", "username");
        text.displayInfo("Login:");
        text.displayInfo("Plaes enter username:");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        console.nextLine();
        System.out.print('\u000C');
        text.title("Welecome to the Prime Events", "Customer", "username");
        text.displayInfo("Login:");
        text.displayInfo("Plaes enter password");
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
        login();
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
