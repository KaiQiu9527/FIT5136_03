import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileIO{
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Owner> owners = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Hall> halls = new ArrayList<>();
    static Text text = new Text();
    static FileReader fileReader;
    static BufferedReader br;

    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        fileIO.startup();
    }

    protected void startup(){
        Customer customer;
        Owner owner;
        Admin admin;
        Hall hall;
        try {
            fileReader = new FileReader("user.txt");
            br = new BufferedReader(fileReader);
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

                if (map.get("usertype").equals("customer"))
                {
                    customer = new Customer(map.get("username"),map.get("fname"),map.get("lname"),
                            map.get("dob"),map.get("password"),map.get("email"),map.get("address"),map.get("phone_no"),Integer.parseInt(map.get("id")));
                    customers.add(customer);
                }
                else if (map.get("usertype").equals("owner"))
                {
                    owner = new Owner(map.get("username"),map.get("fname"),map.get("lname"),
                            map.get("dob"),map.get("password"),map.get("email"),map.get("address"),map.get("phone_no"),Integer.parseInt(map.get("id")));
                    owners.add(owner);
                }
                else {
                    admin = new Admin();
                    admins.add(admin);
                }
            }
            br.close();
            //if not break, means login failed
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            fileReader = new FileReader("hall.txt");
            br = new BufferedReader(fileReader);
            Map<String,String> map = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null){
                line = line.substring(1,line.length()-1);
                ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(line.split(",")));
                for (String e: list1){
                    ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(e.split("=")));
                    map.put(list2.get(0).strip(),list2.get(1).strip());
                }
                hall = new Hall();
                hall.setHallId(Integer.parseInt(map.get("hallId")));
                hall.setOwnerId(Integer.parseInt(map.get("ownerId")));
                hall.setName(map.get("name"));
                hall.setLocation(map.get("location"));
                hall.setSupportEventType(map.get("supportEventType"));
                hall.setPicture(map.get("picture"));
                hall.setDiscount(Double.parseDouble(map.get("discount")));
                halls.add(hall);
            }
            br.close();
            //if not break, means login failed
        } catch (Exception e){
            e.printStackTrace();
        }
   }

    //verify the login
    public User loginVerify(String username, String password_hash){
        //first check customer
        for (int i=0; i<customers.size(); i++){
            if (customers.get(i).getUsername().equals(username) && customers.get(i).getPassword_hash().equals(password_hash)){
                return customers.get(i);
            }
        }
        //then check owner
        for (int i=0; i<owners.size(); i++) {
            if (owners.get(i).getUsername().equals(username) && owners.get(i).getPassword_hash().equals(password_hash)) {
                return owners.get(i);
            }
        }
        //last check admin
        for (int i=0; i<admins.size(); i++) {
            if (admins.get(i).getUsername().equals(username) && admins.get(i).getPassword_hash().equals(password_hash)) {
                return admins.get(i);
            }
        }
        //if nothing match, return null
        return null;
    }

    public void register(Map usermap){
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("user.txt",true));
            pw.println(usermap);
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createAHall(Map hallMap){
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("hall.txt",true));
            pw.println(hallMap);
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getUserAmount() {
        int amount = 0;
        try {
            fileReader = new FileReader("user.txt");
            br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                ++amount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    public int getHallAmount() {
        int amount = 0;
        try {
            fileReader = new FileReader("hall.txt");
            br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                ++amount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    public ArrayList<Hall> getHalls() {
        return halls;
    }
}
