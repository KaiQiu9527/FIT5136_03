import java.util.Scanner;

public class WelcomePage {
    public static void main(String[] args) {
        System.out.println("******************** Hall Booking System ********************");
        System.out.println("Welcome! Please type a number to continue!\nType '1' to login\nType '2' to register\nType '3' to quit");

        while(true) {
            Scanner sc = new Scanner(System.in);
            String uncheckedUserInput = "";
            try {
                uncheckedUserInput = sc.nextLine();
            }catch(Exception e)
            {
                System.out.println("You should input the right number!");
                continue;
            }
            InputVerify inputVerify = new InputVerify();
            //check the validation of input
            int response = inputVerify.checkWhetherInputNumber(uncheckedUserInput);
            //response = 0 means correct input, = 1 means invalid input
            if (response == 0) {
                int userinput = Integer.parseInt(uncheckedUserInput);
                switch (userinput) {
                    case 1:
                        System.out.println("Now turn to the Login Page!");
                        //LoginPage loginpage = new LoginPage();
                        break;
                    case 2:
                        System.out.println("Now turn to the Register Page!");
                        break;
                    case 3:
                        System.out.println("Now Quit!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("You should input the right number!");
                        continue;
                }
            }
            else
            {
                System.out.println("You should input the right number!");
                continue;
            }
            //finish the loop and jump to another page
            break;
        }
    }
}
