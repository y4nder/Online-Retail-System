public class Customer extends User{
   private int customerId;
   private String address;
   
   public Customer(int usId, String usName, String em, int cusId, String addr ){
      super(usId, usName, em);
      customerId = cusId;
      address = addr;
   }
   
   
   public void setCustomerId(int id){
      customerId = id;
   }
   
   public void setAddress(String add){
      address = add;   
   }
   
   public void placeOrder(int prodId){
      
   }
   
   public String toString(){
      return super.toString() + "\n customer id: " + customerId + "\n address: " + address;
   }
}