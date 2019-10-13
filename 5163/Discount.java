import java.security.interfaces.DSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Discount {
    Map<String,Double> discount = new HashMap<>();

    public Discount(){
        discount.put("student",0.5);
        discount.put("member",0.3);
        discount.put("partner",0.3);
    }

   public void modifyDiscount(String type, double discount){
       this.discount.put(type,discount);
   }

   public void deleteDiscount(String type){
        this.discount.remove(type);
   }

   public Map viewAllDiscount(){
        return discount;
   }

   public double getDiscount(String type){
        return this.discount.get(type);
   }


}
