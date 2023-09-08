package RetailSystemFiles;
public class Admin extends User{
   protected int adminId;
   protected String department;
   private Inventory inventory;
   
   public Admin(int usId, String usName, String em, int adId, String dep){
      super(usId, usName, em);
      adminId = adId;
      department = dep;
      inventory = new Inventory();
   }
   
   public Admin(){
      super();
      adminId = 00;
      department = "";
      inventory = new Inventory();
   }

   //setters
   public void setAdminId(int _adminId){
      adminId = _adminId;
   }

   public void setDepartment(String _department){ 
      department = _department; 
   }

   //setters for inventory
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

   

   //getter
   public int getInventoryCount(){
      return inventory.getCounter();
   }

   //methods
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
   
   public String toString(){
      return "admin id: " + adminId + 
            "\n name: " + super.userName + 
            "\n department: " + department + 
            "\n email: " + super.email;
   }
}