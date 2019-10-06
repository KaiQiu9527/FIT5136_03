import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileIO{
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Owner> owners = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();
    static ArrayList<Hall> halls = new ArrayList<>();
    static Map<String, Double> discounts = new HashMap<>();
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
        Hall hall = new Hall();
        halls = new ArrayList<>();
        customers = new ArrayList<>();
        owners = new ArrayList<>();
        admins = new ArrayList<>();
        discounts = new HashMap<>();
        try {
            fileReader = new FileReader("user.txt");
            br = new BufferedReader(fileReader);
            Map<String,String> map = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null){
                if (line.equals(""))
                    continue;
                line = line.replace("{","");
                line = line.replace("}","");
                ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(line.split(",")));
                for (String e: list1){
                    ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(e.split("=")));
                    map.put(list2.get(0).strip(),list2.get(1).strip());
                }
                //check the user input with data

                if (map.get("usertype").equals("customer"))
                {
                    customer = new Customer(map.get("username"),map.get("fname"),map.get("lname"),
                            map.get("dob"),map.get("password"),map.get("email"),map.get("address"),map.get("phone_no"),map.get("id"));
                    customer.setDiscount(map.get("discount"));
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
        } catch (FileNotFoundException ex) {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter("user.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            fileReader = new FileReader("hall.txt");
            br = new BufferedReader(fileReader);
            Map<String,String> map = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null){
                if (line.equals(""))
                    continue;
                line = line.substring(1,line.length()-1);
                ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(line.split(",")));
                for (String e: list1){
                    ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(e.split("=")));
                    map.put(list2.get(0).trim(),list2.get(1).trim());
                }
                hall = new Hall();
                hall.setHallId(Integer.parseInt(map.get("hallId")));
                hall.setOwnerId(Integer.parseInt(map.get("ownerId")));
                hall.setName(map.get("name"));
                hall.setLocation(map.get("location"));
                hall.setSupportEventType(map.get("supportEventType"));
                hall.setPicture(map.get("picture"));
                hall.setDiscount(Double.parseDouble(map.get("discount")));
                String rawDescription = map.get("description");
                String description = rawDescription.replace("*",",");
                map.put("description",description);
                hall.setDescription(map.get("description"));
                halls.add(hall);
            }
            br.close();
            //if not break, means login failed
        }catch (FileNotFoundException ex){
            PrintWriter pw = null;
            try {
                pw = new PrintWriter("hall.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pw.flush();
            pw.close();
        }catch (Exception e){
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
            if (admins.get(i).getUsername().equals(username)) {
                return admins.get(i);
            }
        }
        //if nothing match, return null
        return null;
    }

    public void register(Map userMap){
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("user.txt",true));
            pw.println(userMap);
            pw.println();
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
            pw.println();
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    **读取用户拥有的hall
     */
    public ArrayList<Hall> viewAllHall(){
        FileIO fileIO = new FileIO();
        fileIO.startup();
        return fileIO.halls;
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

    public int getBiggestHallID(){
        int i = 0;
        for (Hall hall : halls){
            if (hall.getHallId() > i)
                i = hall.getHallId();
        }
        return i;
    }

    public int getBiggestUserID(){
        int i = 0;
        for (Customer customer : customers){
            if (Integer.parseInt(customer.getId()) > i)
                i = Integer.parseInt(customer.getId());
        }
        for (Owner owner : owners){
            if (Integer.parseInt(owner.getId()) > i)
                i = Integer.parseInt(owner.getId());
        }
        return i;
    }

    public ArrayList<Hall> getHalls() {
        return halls;
    }

    public void updateHallList(ArrayList<Map<String,String>> maps) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("hall.txt"));
            pw.flush();
            pw.close();
            for (Map<String,String> map : maps){
                createAHall(map);
            }
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }
}
