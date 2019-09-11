import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileIO{
    Text text = new Text();
    User customer = new Customer();
    User owner = new Owner();

    //verify the login
    public User loginVerify(String username, String password_hash){
        FileReader fileReader;
        Boolean login_successfully = false;
        User user;
        try {
            fileReader = new FileReader("user.txt");
            BufferedReader br = new BufferedReader(fileReader);
            Map<String,String> map = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null){
                line = line.substring(1,line.length()-1);
                ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(line.split(",")));
                for (String e: list1){
                    ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(e.split("=")));
                    map.put(list2.get(0).strip(),list2.get(1).strip());
                }
                //check the user input with data
                if (map.get("username").equals(username) && map.get("password").equals(password_hash)){
                    login_successfully = true;
                    if (map.get("usertype").equals("customer"))
                    {
                        user = new Customer(map.get("username"),map.get("fname"),map.get("lname"),
                                map.get("dob"),map.get("password"),map.get("email"),map.get("address"),map.get("phone_no"));
                        return user;
                    }
                    else if (map.get("usertype").equals("owner"))
                    {
                        user = new Owner(map.get("username"),map.get("fname"),map.get("lname"),
                                map.get("dob"),map.get("password"),map.get("email"),map.get("address"),map.get("phone_no"));
                        return user;
                    }
                    else {
                        user = new Admin();
                        return user;
                    }
                }
            }
            //if not break, means login failed
        } catch (FileNotFoundException e) {
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            text.displayInfo("User profile not found. Please check the file or register a new user.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        } catch (
                IOException e) {
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            text.displayInfo("User not found. Please check the file or register a new user.");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        }
        return null;
    }



}
