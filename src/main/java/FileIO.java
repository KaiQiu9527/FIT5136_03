import javax.crypto.AEADBadTagException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileIO{
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Owner> owners = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();
    static ArrayList<Hall> halls = new ArrayList<>();
    static ArrayList<Quotation> quotations = new ArrayList<>();
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
        quotations = new ArrayList<>();
        /**
         * read users from file
         */
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

        /**
         * read halls from file
         */
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
                hall.setPrice(Double.parseDouble(map.get("price")));
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
        /**
         * read quotations from file
         */
        try {
            fileReader = new FileReader("quotation.txt");
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
                Quotation quotation = new Quotation();
                quotation.setQuotationId(Integer.parseInt(map.get("quotationId")));
                quotation.setCustomerId(Integer.parseInt(map.get("customerId")));
                quotation.setHallId(Integer.parseInt(map.get("hallId")));
                quotation.setOwnerId(Integer.parseInt(map.get("ownerId")));
                quotation.setEventType(map.get("eventType"));
                quotation.setEventSize(Integer.parseInt(map.get("eventSize")));
                quotation.setStartTime(new SimpleDateFormat("hh:mm dd-MM-yyyy").parse(map.get("startTime")));
                quotation.setEndTime(new SimpleDateFormat("hh:mm dd-MM-yyyy").parse(map.get("endTime")));
                quotation.setWhetherCatering(Boolean.getBoolean(map.get("whetherCatering")));
                quotation.setState(map.get("state"));
                quotation.setPrice(Double.parseDouble(map.get("price")));
                quotations.add(quotation);
            }
            br.close();
            //if not break, means login failed
        }catch (FileNotFoundException ex){
            PrintWriter pw = null;
            try {
                pw = new PrintWriter("quotation.txt");
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

    public int getBiggestQuotationID(){
        int i = 0;
        for (Quotation quotation : quotations){
            if (quotation.getQuotationId() > i)
                i = quotation.getQuotationId();
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

    public ArrayList<Hall> viewOwnHall(int id){
        ArrayList<Hall> ownHalls = new ArrayList<>();
        for (Hall hall : halls){
            if (hall.getOwnerId() == id)
                ownHalls.add(hall);
        }
        return ownHalls;
    }

    public Hall viewAHall(int hallId){
        for (Hall hall :halls){
            if (hall.getHallId() == hallId)
                return hall;
        }
        return null;
    }

    public void updateDiscount(Hall hall, double discount){
        halls.remove(hall);
        hall.setDiscount(discount);
        halls.add(hall);
        ArrayList<Map<String,String>> maps = new ArrayList<>();
        for (Hall aHall : halls){
            Map<String,String> hallMap = new HashMap<>();
            hallMap.put("hallId",String.valueOf((aHall.getHallId())));
            hallMap.put("ownerId",String.valueOf(aHall.getOwnerId()));
            hallMap.put("name",aHall.getName());
            hallMap.put("location",aHall.getLocation());
            hallMap.put("supportEventType",aHall.getSupportEventType());
            hallMap.put("discount",String.valueOf(aHall.getDiscount()));
            hallMap.put("picture",aHall.getPicture());
            maps.add(hallMap);
        }
        updateHallList(maps);
    }

    public Hall searchAHallByName(String name) {
        for (Hall hall : halls) {
            if (hall.getName().equals(name))
                return hall;
        }
        return null;
    }

    public Hall searchAHallByLocation(String location) {
        for (Hall hall : halls) {
            if (hall.getLocation().equals(location))
                return hall;
        }
        return null;
    }

    /**
    *Customer request for a quotation
     */
    public void askForAQuotation(Quotation quotation){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        Map<String,String> quotationMap = new HashMap<>();
        quotationMap.put("quotationId",String.valueOf(getBiggestQuotationID()+1));
        quotationMap.put("customerId",String.valueOf(quotation.getCustomerId()));
        quotationMap.put("hallId",String.valueOf(quotation.getHallId()));
        quotationMap.put("ownerId",String.valueOf(quotation.getOwnerId()));
        quotationMap.put("eventType", quotation.getEventType());
        quotationMap.put("eventSize",String.valueOf(quotation.getEventSize()));
        String startTime = sdf.format(quotation.getStartTime());
        String endTime = sdf.format(quotation.getEndTime());
        quotationMap.put("startTime",startTime);
        quotationMap.put("endTime",endTime);
        quotationMap.put("whetherCathering",String.valueOf(quotation.getWhetherCatering()));
        quotationMap.put("state",quotation.getState());
        quotationMap.put("price",String.valueOf(quotation.getPrice()));
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("quotation.txt",true));
            pw.println(quotationMap);
            pw.println();
            pw.flush();
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList readCustomerQuotationList(User user){
        startup();
        ArrayList<Quotation> customerQuotationList = new ArrayList<>();
        for (Quotation quotation : quotations){
            if (quotation.getCustomerId() == Integer.parseInt(user.getId()))
                customerQuotationList.add(quotation);
        }
        return customerQuotationList;
    }
}
