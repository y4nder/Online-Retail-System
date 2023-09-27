package RetailSystemFiles;

public class Customer extends User{
   private int customerId;
   private String address;
   private Order order;
   private String orderHistory;

   private boolean hasPurchased;
   private boolean hasOrdered;
   private boolean hasPurchaseHistory;
   private double money;
   
   public Customer(){
      userId = 0;
      userName = "";
      email = "";
      customerId = 0;
      address = "";
      order = new Order();
      orderHistory = "";
      hasPurchased = false;
      hasOrdered = false;
      hasPurchaseHistory = false;;
      money = 500;
   }
   
   //setters
   public void setCustomerId(int id){
      customerId = id;
   }
   
   public void setAddress(String add){
      address = add;   
   }

   //getters
   public int getCustomerId(){
      return customerId;
   }

   public void getOrderCount(){
      System.out.println(order.getCountOrder());
   }

   public boolean getCartStatus(){
      return hasOrdered;
   }

   public double getMoney(){
      return money;
   }

   public boolean HasPurchased(){
      return hasPurchased;
   }

   public boolean HasPurchaseHistory(){
      return hasPurchaseHistory;
   }
   
   //methods
   public boolean placeOrder(Product p, int q){
      order.addProductToOrder(p, q);
      System.out.println("\n" + p.getName().toUpperCase() + " " + q + "X WAS ADDED TO YOUR CART!\n");
      hasOrdered = true;
      return true;
   }

   public void viewOrderHistory(){
      if(hasPurchased == false){
         System.out.println("\nNo History of Purchases\n");
         return;
      }
      System.out.println("-----Order History-----");
      System.out.println(orderHistory);
   }
   
   public boolean viewCart(){
      if(hasOrdered == false){
         System.out.println("\nYOUR CART IS EMPTY :<\n");
         return false;
      }
      System.out.println("\n              ------YOUR CART-----");
      order.displayOrders();
      System.out.println("\n       TOTAL: $" + order.getTotal());
      System.out.println("       Your Balance: " + getMoney() + "\n");
      return true;
   }

   public boolean checkOut(){
      if(getMoney() < order.getTotal()){
         System.out.println("    Insuffiecient Funds lol");
         return false;
      }
      System.out.println("\n-----Order Confirmation---");
      order.confirmOrder(customerId);
      addToHistory(order.getReceipt());
      updateMoney(getMoney() - order.getTotal());
      return true;
   }

   //implementation of abstract methods from class "User"
   public void LogIn(){
      System.out.println("Customer " + customerId + " has logged in");
   }

   public void LogOut(){
      System.out.println("Customer " + customerId + " has logged out");
   }

   //additional methods
   public void addToHistory(String r){
      hasPurchased = true;
      hasPurchaseHistory = true;
      orderHistory += r;
   }

   public boolean pay(double p){
      if(p > getMoney()){
         System.out.println("    You only have " + getMoney());
         return false;
      }
      
      if(p < order.getTotal()){
         System.out.println("    Insuffiecient funds");
         return false;
      }
      else{
         return true;
      }

   }

   public void updateMoney(double mon){
      money = mon;
   }

   //reset methods
   public void resetTotal(){
      order.resetTotal();
   }

   public void clearReceipt(){
      order.clearR();
   }

   public void resetReceipt(){
      order.clearR();
   }

   public void resetCart(){
      order.clearCart();
   }

   public void resetOrders(){
      hasOrdered = false;
   }

   
   public void resetHasPurchased(){
      hasPurchased = false;
   }
   
   public void resetHasPurchaseHistory(){
      hasPurchaseHistory = false;
   }
}