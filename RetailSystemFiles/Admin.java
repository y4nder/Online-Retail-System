package RetailSystemFiles;
public class Admin extends User{
   protected int adminId;
   protected String department;
   private Inventory inventory;
      
   public Admin(){
      userId = 0;
      userName = "";
      email = "";
      adminId = 00;
      department = "";
      inventory = new Inventory();
   }

   public void setAdminId(int adminId){
      this.adminId = adminId;
   }

   public void setDepartment(String department){ 
      this.department = department; 
   }

   //methods for managing inventory
   public boolean addProduct(Product p){
      inventory.add(p);
      return true;
   }

   public boolean removeProduct(int index){
      inventory.removeFromInventory(index);
      return true;
   }

   public boolean manageInventory(int index, int qty){ //for changing stock
      inventory.updateInventoryStock(index, qty);
      return true;
   }
   public boolean manageInventory(int index){ //for adding stock
      inventory.refillInventory(index);
      return true;
   }
   public boolean deductStock(int index, int qty){
      inventory.deductInventoryStock(index, qty);
      return true;
   }

   public void editProductId(int id, int index){
      inventory.editId(id, index);
   }

   public void editProductName(String name, int index){
      inventory.editName(name, index);
   }

   public void editProductPrice(double price, int index){
      inventory.editPrice(price, index);
   }

   public void editProductQuantity(int qty, int index){
      inventory.editQuantity(qty, index);
   }

   public int getInventoryCount(){
      return inventory.getCounter();
   }

   //implementation of abstract methods from class "User"
   public void LogIn(){
      System.out.println("Admin " + userName + " has logged in");
   }

   public void LogOut(){
      System.out.println("Admin " + userName + " has logged out");
   }
   
   //additional methods
   public void lookAtInventoryForAdmin(){
      inventory.showInventoryForAdmin();
   }

   public void lookAtInventoryForCustomer(){
      inventory.showInventoryForCustomer();
   }

   public Product getProduct(int index){
      return inventory.findProduct(index);
   }
   
   public Product createProduct(){
      return inventory.createNewProduct();
   }

   public void soldReset(){
      inventory.resetProducts();
   }

   public void sellTheProduct(){
      inventory.sellProd();
   }
   
}