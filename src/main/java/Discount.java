import java.util.HashMap;
import java.util.Map;

/**
 * Discount is the entity class to represent customer's type and discount in our program.
 * Discount class has one Map type attribute discount, it store the data of customer types and their assigned discount.
 *
 * @version 1.1
 * @author FIT 5136 2019 sem 2, Monday 10:am Group 3
 */
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
