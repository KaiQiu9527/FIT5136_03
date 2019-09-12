import java.util.Scanner;

public class UI {
    /**
     * Generate the left space for displaying UI
     * @param input the content
     * @return the left space for content
     */
    private String leftSpace(String input) {
        int length = 71;
        String leftSpace = " ";
        String rightSpace = " ";
        while (leftSpace.length() + rightSpace.length() < (length - input.length()))
        {
            leftSpace = leftSpace + " ";
            if (leftSpace.length() + rightSpace.length() < (length - input.length()))
                rightSpace = rightSpace + " ";
        }
        return leftSpace;
    }

    /**
     * Generate the right space for displaying UI
     * @param input the content
     * @return the right space for content
     */
    private String rightSpace(String input) {
        int length = 71;
        String leftSpace = " ";
        String rightSpace = " ";
        while (leftSpace.length() + rightSpace.length() < (length - input.length()))
        {
            leftSpace = leftSpace + " ";
            if (leftSpace.length() + rightSpace.length() < (length - input.length()))
                rightSpace = rightSpace + " ";
        }
        return rightSpace;
    }

    /**
     * Generate the simple title part of UI
     * @param title the title content
     */
    private void simpleTitle(String title) {
        String leftSpace = leftSpace(title);
        String rightSpace = rightSpace(title);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("+                                                                       +");
        System.out.println("+" + leftSpace + title + rightSpace + "+");
        System.out.println("+                                                                       +");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Generate the title part of UI, including user information
     * @param title the title content
     * @param type the user type
     * @param username the username
     */
    private void title(String title, String type, String username) {
        String displayUser = type + "  : " + username;
        String ls1 = leftSpace(title);
        String rs1 = rightSpace(title);
        String ls2 = leftSpace(displayUser);
        String rs2 = rightSpace(displayUser);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("+                                                                       +");
        System.out.println("+" + ls1 + title + rs1 + "+");
        System.out.println("+" + ls2 + displayUser + rs2 + "+");
        System.out.println("+                                                                       +");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display one line in the UI
     * @param info the content
     */
    public void displayInfo(String info) {
        int length = 71;
        String space = " ";
        while (space.length() < (length - info.length())) {
            space = space + " ";
        }
        System.out.println("+" + info + space + "+");
    }

    /**
     * Display hall information
     * @param input the hall info
     */
    private void displayHall(String input) {
        System.out.println("+Hall " + input + ": information displays here                                       +");
        System.out.println("+Name:                                                                  +");
        System.out.println("+Location:                                                              +");
        System.out.println("+Description:                                                           +");
        System.out.println("+-----------------------------------------------------------------------+");
    }

    /**
     * Display booking information
     * @param input the booking info
     */
    private void displayBooking(String input) {
        System.out.println("+Booking " + input + ": information displays here                                    +");
        System.out.println("+Event time:                                                            +");
        System.out.println("+Location:                                                              +");
        System.out.println("+Deposit paid:                                                          +");
        System.out.println("+-----------------------------------------------------------------------+");
    }
    /**
     * Display the lower part of the UI, like return, log out function
     * @param input the content to display in the lower part
     */
    private void displayLowerPart(String input){
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        displayInfo(input);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the login page
     */
    public void loginPage() {
        System.out.print('\u000C');
        simpleTitle("Welcome to Prime Events");
        displayInfo("   Select your choice:");
        displayInfo("1. Log in");
        displayInfo("2. New user sign up");
        displayInfo("3. Forget password");
        displayInfo("4. Exit");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the login steps
     * @param input the content to determine which step of login
     */
    public void loginStep(String input){
        System.out.print('\u000C');
        simpleTitle("LOGIN");
        displayInfo("Please enter your " +  input + ":");
        displayLowerPart("Q. Quit login");
    }

    /**
     * Display the verification page for login
     * @param flag the flag for verification
     */
    public void loginVerification(boolean flag){
        System.out.print('\u000C');
        simpleTitle("LOGIN");
        if (flag)
            displayInfo("Login success!");
        else
            displayInfo("Username or password is not correct, please enter again.");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the register page
     */
    public void registerPage() {
        System.out.print('\u000C');
        simpleTitle("REGISTER");
        displayInfo("Please select your user type:");
        displayInfo("1. Customer");
        displayInfo("2. Owner");
        displayLowerPart("Q. Quit registration");
    }

    /**
     * Display the message for successfully sign up
     *
     * @param input the String to determine the step of register
     */
    public void registerEnter(String input) {
        System.out.print('\u000C');
        simpleTitle("REGISTER");
        displayInfo("Please enter your " +  input + ":");
    }

    /**
     * Display the message for successful registration
     */
    public void registerSuccess() {
        System.out.print('\u000C');
        simpleTitle("REGISTER");
        displayInfo("Register Success!");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the reset password page and steps
     * @param input the String to determine the step of reset password
     */
    public void resetPassword(String input) {
        System.out.print('\u000C');
        simpleTitle("RESET PASSWORD");
        displayInfo("Please enter " +  input + ":");
        displayLowerPart("Q. Quit reset password");
    }

    public void logOutSuccess() {
        System.out.print('\u000C');
        simpleTitle("LOG OUT SUCCESS!");
    }

    /**
     * Display the main menu of customer
     */
    public void customerMainMenu(String username) {
        System.out.print('\u000C');
        title("MAIN MENU", "Customer", username);
        displayInfo("   Enter the choice:");
        displayInfo("1. View the halls");
        displayInfo("2. Search a hall");
        displayInfo("3. View the quotations");
        displayInfo("4. Manage bookings");
        displayInfo("5. Manage account");
        displayLowerPart("Q. Log out");
    }

    /**
     * Display the main page of view halls (need modify to display details)
     */
    public void viewHalls() {
        System.out.print('\u000C');
        title("VIEW THE HALLS", "Customer", "username");
        displayHall("1");
        displayHall("2");
        displayHall("3");
        displayInfo("   Enter the choice:");
        displayInfo("   Enter 1, 2 or 3 to select the hall");
        displayInfo("4. Next page");
        displayInfo("5. Previous page");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the UI of customer select a hall (need modify to display details)
     */
    public void customerSelectHall() {
        System.out.print('\u000C');
        title("VIEW THE HALLS", "Customer", "username");
        displayHall("hall name");
        displayInfo("   Enter the choice:");
        displayInfo("1. Send a request for quotation");
        displayInfo("2. View comments of the hall");
        displayLowerPart("R. Return to View the halls");
    }

    public void searchAHall() {
        System.out.print('\u000C');
        title("SEARCH A HALL", "Customer", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Send a request for quotation");
        displayInfo("2. View comments of the hall");
        displayLowerPart("R. Return to View the halls");
    }

    /**
     * Display the UI of sending request for quotation (need modify to display details)
     * @param input the String to determine the step of sending request for quotation
     */
    public void sendRequestStep(String input) {
        System.out.print('\u000C');
        title("SEND REQUEST FOR QUOTATION", "Customer", "username");
        displayHall("hall name");
        displayInfo("Please enter " +  input + ":");
        displayLowerPart("Q. Quit sending request");
    }

    /**
     * Display the confirm page of request for quotation (need modify to display details)
     */
    public void confirmRequest() {
        System.out.print('\u000C');
        title("SEND REQUEST FOR QUOTATION", "Customer", "username");
        displayHall("hall name");
        displayInfo("Request Info here");
        displayInfo("Are you sure to send this request for quotation?");
        displayInfo("Y. Confirm sending this request");
        displayLowerPart("Q. Quit sending request");
    }

    /**
     * Display the UI of selecting a time for booking
     */
    public void selectTime() {
        System.out.print('\u000C');
        title("SEND REQUEST FOR QUOTATION", "Customer", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Morning time   08:00 to 12:00");
        displayInfo("2. Afternoon time 13:00 to 17:00");
        displayInfo("3. Evening        18:00 to 22:00");
        displayLowerPart("Q. Quit sending request");
    }

    public void sendRequestSuccess() {
        System.out.print('\u000C');
        title("SEND REQUEST FOR QUOTATION", "Customer", "username");
        displayHall("hall name");
        displayInfo("Request for quotation sent successfully.");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the UI of viewing the quotations (need modify to display details)
     */
    public void viewQuotations() {
        System.out.print('\u000C');
        title("VIEW THE QUOTATIONS", "Customer", "username");
        displayHall("hall name");
        displayInfo("   Enter the choice:");
        displayInfo("1. Accept the quotation");
        displayInfo("2. Decline the quotation");
        //might have multiple quotations received
        displayInfo("3. View the next quotation");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the confirm page of making payment (need modify to display details)
     */
    public void paymentConfirm() {
        System.out.print('\u000C');
        title("CONFIRM PAYMENT", "Customer", "username");
        displayHall("payment amount:");
        displayInfo("Are you sure to pay the deposit?");
        displayInfo("Y. Confirm paying the deposit");
        //might have multiple quotations received
        displayLowerPart("Q. Quit payment");
    }

    /**
     * Display the main page of manage bookings (need modify to display details)
     */
    public void manageBookings() {
        System.out.print('\u000C');
        title("MANAGE BOOKINGS", "Customer", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Sample booking 1");
        displayInfo("2. Sample booking 2");
        displayInfo("3. Sample booking 3");
        displayInfo("4. Next page");
        displayInfo("5. Previous page");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the UI of customer select a booking (need modify to display details)
     */
    public void customerSelectBookings() {
        System.out.print('\u000C');
        title("MANAGE BOOKINGS", "Customer", "username");
        displayBooking("booking no");
        displayInfo("   Enter the choice:");
        displayInfo("1. Change the booking date");
        displayInfo("2. Cancel the booking");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the confirm page of cancelling
     */
    public void customerCancelConfirm() {
        System.out.print('\u000C');
        title("CANCEL BOOKING", "Customer", "username");
        displayInfo("   Are you sure about cancel this booking?");
        displayInfo("   ---------------------------------------");
        displayInfo("Y. Yes, I want to cancel it.");
        displayInfo("N. No");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the change the booking date UI
     */
    public void changeTheBookingDate() {
        System.out.print('\u000C');
        title("CHANGE THE BOOKING DATE", "Customer", "username");
        displayInfo("   New booking time should not be within 1 day.");
        displayInfo("   --------------------------------------------");
        displayInfo("Please enter the new date:");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the fail page for input invalid date
     */
    public void changeBookingFail() {
        System.out.print('\u000C');
        title("CHANGE THE BOOKING DATE", "Customer", "username");
        displayInfo("   The input date is not valid or within 1 day.");
        displayInfo("   --------------------------------------------");
        displayInfo("   Please enter it again.");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the fail page for a busy time
     */
    public void timeBusyFail() {
        System.out.print('\u000C');
        title("CHANGE THE BOOKING DATE", "Customer", "username");
        displayInfo("   Oops! The new date is not available.");
        displayInfo("   -----------------------------------------");
        displayInfo("   Please enter it again.");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the success page for change the booking date
     */
    public void changeBookingDateSuccess() {
        System.out.print('\u000C');
        title("CHANGE THE BOOKING DATE", "Customer", "username");
        displayHall("hall name");
        displayInfo("Booking date changed successfully.");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the main page of manage account (need modify to display details)
     */
    public void manageAccount() {
        System.out.print('\u000C');
        title("MANAGE ACCOUNT", "Customer", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Change password");
        displayInfo("2. Update personal details");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display customer change password page
     * @param input the content to determine which step of change password
     */
    public void CustomerChangePassword(String input) {
        System.out.print('\u000C');
        title("CHANGE PASSWORD", "Customer", "username");
        displayInfo("Please enter your " +  input + ":");
        displayLowerPart("R. Return to the Manage account");
    }

    /**
     * Display customer update details page
     * @param input the content to determine which step of updating details
     */
    public void CustomerUpdateDetails(String input) {
        System.out.print('\u000C');
        title("UPDATE DETAILS", "Customer", "username");
        displayInfo("Please enter your " +  input + ":");
        displayLowerPart("R. Return to the Manage account");
    }

    /**
     * Display the main menu of administrator
     */
    public void adminMainMenu(String username) {
        System.out.print('\u000C');
        title("MAIN MENU", "Admin", username);
        displayInfo("   Enter the choice:");
        displayInfo("1. Manage users");
        displayInfo("2. Manage discounts");
        displayLowerPart("Q. Log out");
    }

    /**
     * Display the admin manage user page
     */
    public void manageUsers() {
        System.out.print('\u000C');
        title("MANAGE USERS", "Admin", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Reset password");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the admin reset password page and steps
     * @param input the String to determine the step of reset password
     */
    public void adminResetPassword(String input) {
        System.out.print('\u000C');
        title("RESET PASSWORD", "Admin", "username");
        displayInfo("Please enter " +  input + ":");
        displayLowerPart("Q. Quit reset password");
    }

    /**
     * Display the admin manage discounts page
     */
    public void manageDiscounts() {
        System.out.print('\u000C');
        title("MANAGE DISCOUNT", "Admin", "username");
        displayInfo("   Select the customer type for setting discount:");
        displayInfo("1. Set discount for senior citizens");
        displayInfo("2. Set discount for veterans");
        displayInfo("3. Set discount for normal customers");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the admin manage discounts page (need modify to display details)
     */
    public void updateDiscounts() {
        System.out.print('\u000C');
        title("UPDATE DISCOUNT", "Admin", "username");
        displayInfo("   You are updating discount for: ");
        displayInfo("   Enter the discount:");
        displayLowerPart("R. Return to the Manage discounts");
    }

    public void displayUI() {
        Scanner console = new Scanner(System.in);
        loginPage();
        console.nextLine();
        loginStep("username");
        console.nextLine();
        loginStep("password");
        console.nextLine();
        loginVerification(true);
        console.nextLine();
        registerPage();
        console.nextLine();
        registerEnter("first name");
        console.nextLine();
        registerEnter("last name");
        console.nextLine();
        registerEnter("email");
        console.nextLine();
        registerEnter("username");
        console.nextLine();
        registerEnter("password");
        console.nextLine();
        registerEnter("password again");
        console.nextLine();
        registerSuccess();
        console.nextLine();
    }
}
