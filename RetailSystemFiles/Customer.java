package RetailSystemFiles;

public class Customer extends User{
   private int customerId;
   private String address;
   private Order order;
   private String orderHistory;
   private boolean hasPurchased;
   private boolean cancelled;
   private boolean hasOrdered;
   
   public Customer(int usId, String usName, String em, int cusId, String addr ){
      super(usId, usName, em);
      customerId = usId;
      address = addr;
      order = new Order();
      orderHistory = "";
      hasPurchased = false;
      cancelled = false;
      hasOrdered = false;
   }

   public Customer(){
      customerId = 0;
      address = "";
      order = new Order();
      orderHistory = "";
      hasPurchased = false;
      cancelled = false;
      hasOrdered = false;
   }
   
   //setters
   public void setCustomerId(int id){
      customerId = id;
   }
   
   public void setAddress(String add){
      address = add;   
   }

   public void resetTotal(){
      order.resetTotal();
   }

   public void clearReceipt(){
      cancelled = true;
      order.clearR();
   }

   public void resetReceipt(){
      order.clearR();
   }

   public void resetOrders(){
      hasOrdered = false;
   }

   public void getOrderCount(){
      System.out.println(order.getCountOrder());
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
   
   public void viewCart(){
      if(hasOrdered == false){
         System.out.println("\ncart is empty\n");
         return;
      }
      System.out.println(order.getReceipt());
   }

   public void checkOut(){
      hasPurchased = true;
      System.out.println("\n-----Order Confirmation---");
      order.confirmOrder();
      addToHistory(order.getReceipt());
   }

   public void addToHistory(String r){
      hasPurchased = true;
      orderHistory += r;
   }

   public String toString(){
      return  "customer id: " + customerId + 
               "\nName: " + super.userName + 
               "\naddress: " + address + 
               "\nemail: " + super.email;
   }
}