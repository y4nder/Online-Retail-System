public class Product {
   private int productId;
   private String name;
   private double price;
   private int quantity;
   
   public Product(int prodId, String n, double p, int q ){
      productId = prodId;
      name = n;
      price = p;
      quantity = q;
   }
   
      
   public Product(){
      productId = 00;
      name = "";
      price = 0;
      quantity =0;
   }
   
   public String toString(){
      return "product id: " + productId + "\nname: " + name + "\nprice: " + price + "\nquanity: " + quantity;
   }
}