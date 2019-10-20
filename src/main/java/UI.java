import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * The UI class is the boundary class in our program.
 * It is used to display information for all kinds of user of our program.
 *
 * @version 1.4
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
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
     */
    public void displayHall(Hall hall) {
        System.out.println("+Name: " + hall.getName());
        System.out.println("+Hall ID : " + hall.getHallId());
        System.out.println("+Owner ID: " + hall.getOwnerId());
        System.out.println("+Hall Location: " + hall.getLocation());
        System.out.println("+Hall Support Event Type: " + hall.getSupportEventType());
        System.out.println("+Discount: " + hall.getDiscount());
        System.out.println("+Picture: " + hall.getPicture());
        System.out.println("+Description: " + hall.getDescription());
        System.out.println(("+Price: " + hall.getPrice()));
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println();
    }

    /**
     * Display booking information
     */
    public void displayBooking(Booking booking) {
        displayInfo("Booking information: ");
        displayInfo("Booking ID: " + booking.getBookingId());
        displayInfo("Hall ID: "+ booking.getHallId());
        displayInfo("Owner ID: "+ booking.getOwnerId());
        displayInfo("Event Type: "+ booking.getEventType());
        displayInfo("Event Size: "+ booking.getEventSize());
        displayInfo("Start Time: "+ booking.getStartTime());
        displayInfo("End Time: "+ booking.getEndTime());
        displayInfo("Whether catering: "+ booking.getWhetherCatering());
        displayInfo("Price: "+ booking.getPrice());
        displayInfo("State: " + booking.getState());
    }

    /**
     * Display request information
     * @param input the request info
     */
    private void displayRequest(String input) {
        System.out.println("+Request " + input + ": information displays here                                    +");
        System.out.println("+Event time:                                                            +");
        System.out.println("+Hall name:                                                             +");
        System.out.println("+Number of people:                                                      +");
        System.out.println("+Whether catering:                                                      +");
        System.out.println("+Requirements:                                                          +");
        System.out.println("+-----------------------------------------------------------------------+");
    }
    /**
     * Display the lower part of the UI, like return, log out function
     * @param input the content to display in the lower part
     */
    public void displayLowerPart(String input){
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
     * Display the message for register
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
    public void viewHallList(ArrayList<Hall> halls,String username) {
        System.out.print('\u000C');
        title("VIEW THE HALLS", "Customer", username);
        int i = 1;
        for (Hall hall : halls){
            displayInfo(i+".");
            displayHall(hall);
            i++;
        }
        displayInfo("   Enter the choice:");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the UI of customer select a hall (need modify to display details)
     */
    public void customerSelectHall(Hall hall) {
        System.out.print('\u000C');
        title("VIEW THE HALLS", "","");
        displayHall(hall);
        displayInfo("   Enter the choice:");
        displayInfo("1. Send a request for quotation");
        displayInfo("2. View comments of the hall");
        displayLowerPart("R. Return to View the halls");
    }

    /**
     * Display the UI of sending request for quotation (need modify to display details)
     * @param input the String to determine the step of sending request for quotation
     */
    public void sendRequestStep(Hall hall, String input) {
        System.out.print('\u000C');
        title("SEND REQUEST FOR QUOTATION", "Customer", "username");
        displayHall(hall);
        displayInfo("Please enter " +  input + ":");
        displayLowerPart("Q. Quit sending request");
    }

    /**
     * Display the confirm page of request for quotation (need modify to display details)
     */
    public void confirmSendRequest() {
        System.out.print('\u000C');
        title("SEND REQUEST FOR QUOTATION", "Customer", "username");
        //displayHall("hall name");
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
        //displayHall("hall name");
        displayInfo("Request for quotation sent successfully.");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    /**
     * Display the main page of search a hall
     */
    public void searchAHall() {
        System.out.print('\u000C');
        title("SEARCH A HALL", "Customer", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Search a hall by name");
        displayInfo("2. Search a hall by event type");
        displayInfo("3. Search a hall by location");
        displayLowerPart("0. Return to the Main menu");
    }

    /**
     * Display the page of search a hall by location or name (event type need to be selected)
     */
    public void searchAHallBy(String something) {
        System.out.print('\u000C');
        title("SEARCH A HALL", "","");
        displayInfo("   Search a hall by " + something + ":");
        displayInfo("   Please enter the " + something);
        displayLowerPart("R. Return to Search a Hall");
    }

    /**
     * Display the page of search a hall by location or name (event type need to be selected)
     */
    public void searchAHallByEventType() {
        System.out.print('\u000C');
        title("SEARCH A HALL", "Customer", "username");
        displayInfo("   Search a hall by event type:");
        displayInfo("   Please select the event type you are interested:");
        displayInfo("1. Wedding ceremony");
        displayInfo("2. Wedding reception");
        displayInfo("3. Birthday");
        displayInfo("4. Anniversary");
        displayLowerPart("R. Return to Search a Hall");
    }

    /**
     * Display the UI of viewing the quotations (need modify to display details)
     */
    public void viewQuotations(ArrayList<Quotation> quotations) {
        System.out.print('\u000C');
        title("VIEW THE QUOTATIONS", "","");
        int index = 1;
        for (Quotation quotation : quotations){
            displayInfo(String.valueOf(index));
            displayQuotation(quotation);
            displayInfo("");
            index++;
        }
//        displayInfo("   Enter the choice:");
//        displayInfo("1. Accept the quotation");
//        displayInfo("2. Decline the quotation");
//        //might have multiple quotations received
//        displayInfo("3. View the next quotation");
        displayLowerPart("R. Return to the Main menu");
    }

    public void displayQuotation(Quotation quotation) {
        if (quotation.getQuotationId() != 0) {
            displayInfo("Quotation ID: " + quotation.getQuotationId());
            displayInfo("Customer ID: " + quotation.getCustomerId());
            displayInfo("Hall ID: " + quotation.getHallId());
            displayInfo("Owner ID: " + quotation.getOwnerId());
            displayInfo("Event type: " + quotation.getEventType());
            displayInfo("Event size: " + quotation.getEventSize());
            displayInfo("Start time: : " + quotation.getStartTime());
            displayInfo("End time: : " + quotation.getEndTime());
            displayInfo("Whether catering: " + quotation.getWhetherCatering());
            displayInfo("State: " + quotation.getState());
            displayInfo("Price: " + quotation.getPrice());
        }
    }

    public void displayQuotationOperation()
    {
        displayInfo("Are you sure to send this request for quotation?");
        displayInfo("Y. Confirm sending this request");
        displayLowerPart("Q. Quit sending request");
    }

    /**
     * Display the confirm page of making payment (need modify to display details)
     */
    public void paymentConfirm() {
        System.out.print('\u000C');
        title("CONFIRM PAYMENT", "Customer", "username");
        displayInfo("payment amount:");
        displayInfo("Are you sure to pay the deposit?");
        displayInfo("Y. Confirm paying the deposit");
        //might have multiple quotations received
        displayLowerPart("Q. Quit payment");
    }

    /**
     * Display the main page of manage bookings (need modify to display details)
     */
    public void manageBookings(ArrayList<Booking> bookings) {
        System.out.print('\u000C');
        title("MANAGE BOOKINGS", "Customer", "");
        int i = 0;
        for (Booking booking : bookings){
            i++;
            displayInfo(i+". ");
            displayBooking(booking);
        }
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the UI of customer select a booking (need modify to display details)
     */
    public void customerSelectBookings() {
        System.out.print('\u000C');
        title("MANAGE BOOKINGS", "Customer", "username");
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
        displayInfo("   Are you sure to cancel this booking?");
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
    public void updateDiscounts(Hall hall) {
        System.out.print('\u000C');
        title("UPDATE DISCOUNT", "Hall Name", hall.getName());
        displayInfo("   You are updating discount for: " + hall.getName());
        displayInfo("   Enter the discount:");
        displayLowerPart("R. Return to the Manage discounts");
    }

    /**
     * Display the owner's main menu (need modify to display details)
     * @param username owner's username
     */
    public void ownerMainMenu(String username) {
        System.out.print('\u000C');
        title("MAIN MENU", "Owner", username);
        displayInfo("   Enter the choice:");
        displayInfo("1. Create a hall");
        displayInfo("2. Manage halls");
        displayInfo("3. View Requests");
        //manage bookings includes cancel bookings and refund payment
        displayInfo("4. Manage bookings");
        displayInfo("5. Manage account");
        displayInfo("6. Manage Payment");
        displayLowerPart("Q. Log out");
    }

    /**
     * Display the page of create a hall (need modify to display details)
     * @param content the content needed input
     */
    public void createAHall(String content) {
        System.out.print('\u000C');
        //title("CREATE A HALL", "Owner", "username");
        displayInfo("   Please enter the " + content);
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the select event type page
     */
    public void createAHallType() {
        System.out.print('\u000C');
        //title("CREATE A HALL", "Owner", "username");
        displayInfo("  Please select a event type");
        displayInfo("   If multiple types, please enter numbers splits with a comma");
        displayInfo("1. Wedding ceremony");
        displayInfo("2. Wedding reception");
        displayInfo("3. Birthday");
        displayInfo("4. Anniversary");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display manage halls page (need modify to display details)
     */
    public void manageHalls(ArrayList<Hall> halls) {
        System.out.print('\u000C');
        title("MANAGE HALLS", "","");
        displayInfo("   Enter the Hall ID:");
        for(int i=0; i<halls.size(); i++)
        {
            displayHall(halls.get(i));
        }
        displayInfo("N. Next page");
        displayInfo("P. Previous page");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display managing page of selected hall
     */
    public void manageSelectedHall(Hall hall) {
        System.out.print('\u000C');
        title("MANAGE HALLS", "Owner", "username");
        displayHall(hall);
        displayInfo("   Enter the choice:");
        displayInfo("1. Update hall information");
        displayInfo("2. Update discount for this hall");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display managing options of selected hall
     */
    public void updateHall(Hall hall) {
        System.out.print('\u000C');
        title("MANAGE HALLS", "Hall Name", hall.getName());
        displayHall(hall);
        displayInfo("   Enter the choice:");
        displayInfo("1. Update Hall Name");
        displayInfo("2. Update Hall Location");
        displayInfo("3. Update Support Event Type");
        displayInfo("4. Update Picture");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the main page of view requests
     */
    public void viewRequests() {
        System.out.print('\u000C');
        title("VIEW REQUESTS", "Owner", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Sample request 1");
        displayInfo("2. Sample request 2");
        displayInfo("3. Sample request 3");
        displayInfo("4. Next page");
        displayInfo("5. Previous page");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the page of view selected request
     */
    public void viewSelectedRequest() {
        System.out.print('\u000C');
        title("VIEW REQUESTS", "Owner", "username");
        displayRequest("request no");
        displayInfo("   Enter the choice:");
        displayInfo("1. Accept this request");
        displayInfo("2. Decline this request");
        displayLowerPart("R. Return to the View Request");
    }

    /**
     * Display the page of accept request
     */
    public void acceptRequest() {
        System.out.print('\u000C');
        title("VIEW REQUESTS", "Owner", "username");
        displayRequest("request no");
        displayInfo("   Enter the price for this offer:");
        displayLowerPart("R. Return to the View Request");
    }

    /**
     * Display the page of owner send quotation
     */
    public void sendQuotationConfirm() {
        System.out.print('\u000C');
        title("VIEW REQUESTS", "", "");
        displayRequest("request no");
        displayInfo("Are you sure to send this quotation to customer?");
        displayInfo("Y. Confirm sending this quotation");
        displayLowerPart("Or quit sending quotation");
    }

    /**
     * Display the main page of owner manage bookings (need modify to display details)
     */
    public void ownerManageBookings() {
        System.out.print('\u000C');
        title("MANAGE BOOKINGS", "Owner", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Sample booking 1");
        displayInfo("2. Sample booking 2");
        displayInfo("3. Sample booking 3");
        displayInfo("4. Next page");
        displayInfo("5. Previous page");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Display the UI of owner select a booking (need modify to display details)
     */
    public void ownerSelectBookings() {
        System.out.print('\u000C');
        title("MANAGE BOOKINGS", "Customer", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Cancel the booking");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the confirm page of owner cancelling booking
     */
    public void ownerCancelConfirm() {
        System.out.print('\u000C');
        title("CANCEL BOOKING", "Owner", "username");
        displayInfo("   Are you sure to cancel this booking?");
        displayInfo("   ---------------------------------------");
        displayInfo("Y. Yes, I want to cancel it.");
        displayInfo("N. No");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the confirm page of owner refund payment
     */
    public void ownerRefundPayment() {
        System.out.print('\u000C');
        title("CANCEL BOOKING", "Owner", "username");
        displayInfo("   This booking has already be cancelled.");
        displayInfo("   Do you want to refund this payment to the customer?.");
        displayInfo("   ---------------------------------------");
        displayInfo("Y. Yes, I want to do it.");
        displayInfo("N. No");
        displayLowerPart("R. Return to Manage bookings");
    }

    /**
     * Display the main page of owner manage payment (need modify to display details)
     */
    public void ownerManagePayment() {
        System.out.print('\u000C');
        title("MANAGE PAYMENT", "Owner", "username");
        displayInfo("   Enter the choice:");
        displayInfo("1. Sample cancelled booking 1");
        displayInfo("2. Sample cancelled booking 2");
        displayInfo("3. Sample cancelled booking 3");
        displayInfo("4. Next page");
        displayInfo("5. Previous page");
        displayLowerPart("R. Return to the Main menu");
    }

    /**
     * Method of displaying the UI
     */
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

    public void createHallSuccess() {
        System.out.print('\u000C');
        simpleTitle("Create A Hall");
        displayInfo("Create Success!");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    public void displayCreateSuccessful(Map<String,String> hallMap){
        displayInfo("   These are the hall information you just created: ");
        displayInfo("   Hall ID: " + hallMap.get("hallId"));
        displayInfo("   Owner ID: " + hallMap.get("ownerId"));
        displayInfo("   Hall Name: " + hallMap.get("name"));
        displayInfo("   Hall Location: " + hallMap.get("location"));
        displayInfo("   Support Event Type: " + hallMap.get("supportEventType"));
        displayInfo("   Hall Discount: " + hallMap.get("discount"));
        displayInfo("   Hall Picture: " + hallMap.get("picture"));
        String rawDescription = hallMap.get("description");
        String description = rawDescription.replace("*",",");
        displayInfo("   Hall Description: " + description);
        displayInfo("   Price: " + hallMap.get("price"));
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    public void customerOperateAQutation() {
        System.out.print('\u000C');
        displayInfo("   Enter the choice:");
        displayInfo("1. Make a booking");
        displayInfo("2. Decline the quotation");
        displayLowerPart("R. Return to the Main menu");
    }

    public void ownerOperateAQuotation() {
        System.out.print('\u000C');
        displayInfo("   Enter the choice:");
        displayInfo("1. Send the quotation");
        displayInfo("2. Reject the request");
        displayLowerPart("R. Return to the Main menu");
    }

    public void displayBookingOperation() {
        System.out.print('\u000C');
        displayInfo("   Enter the choice:");
        displayInfo("1. Change the booking date");
        displayInfo("2. Cancel the booking");
        displayLowerPart("R. Return to the Main menu");

    }
}
