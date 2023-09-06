package RetailSystemFiles;
public class Inventory {
    private int MAX;
    private Product[] list;
    private int counter;
    private int maxQuantity;

    public Inventory() {
        MAX = 20;
        list = new Product[MAX];
        list[0] = new Product(1, "bacon", 19.99, 4);
        list[1] = new Product(34, "pork", 5.99, 6);
        list[2] = new Product(65, "Shabu", 4.50, 8);
        list[3] = new Product(21, "Vape", 60, 10);
        counter = 4;
        maxQuantity = 99;
    }

    //setters
    public void editId(int id, int index){
        list[index - 1].setProductId(id);
    }

    public void editName(String name, int index){
        list[index - 1].setProductName(name);
    }

    public void editPrice(double price, int index){
        list[index - 1].updatePrice(price);
    }

    public void editQuantity(int qty, int index){
        list[index - 1].updateStock(qty);
    }

    public int getCounter(){
        return counter;
    }
    //methods
    public boolean add(Product p){
        if(counter >= MAX){
            System.out.println("Maximum limit of Products made");
            return false;
        }
        counter++;
        list[counter - 1] = p;
        return true;
    }

    public boolean removeFromInventory(int index){
        if(counter == 0){
            System.out.println("Your Inventory is empty");
            return false;
        }

        for(int i = index - 1; i < counter - 1; i++){
            list[i] = list[i + 1];
        }
        counter--;
        System.out.println(list[index-1].getName() + " has been removed from storage");
        return true;
    }

    public boolean updateInventoryStock(int index, int qty){
        list[index - 1].updateStock(qty);
        return true;
    }

    public boolean deductInventoryStock(int index, int qty){
        list[index - 1].updateStock(list[index - 1]. getQuantity() - qty);
        return true;
    }

    public void showInventoryForAdmin(){
        System.out.printf("   %15s %15s %15s %15s\n", "----------", "----------", "----------", "----------");
        System.out.printf("   %15s %15s %15s %15s\n", "ID", "NAME", "PRICE", "QUANTITY");
        System.out.printf("   %15s %15s %15s %15s\n", "----------", "----------", "----------", "----------");
        System.out.println();
        for(int i = 0; i < counter; i++){
            System.out.print("[" + (i + 1) + "]");
            list[i].showInfoForAdmin();
        }
    }

    public void showInventoryForCustomer(){
        System.out.printf("   %15s %15s %15s\n", "----", "-----", "------");
        System.out.printf("   %15s %15s %15s\n","NAME", "PRICE", "QUANTITY");
        System.out.printf("   %15s %15s %15s\n", "----", "-----", "------");
        System.out.println();
        for(int i = 0; i < counter; i++){
            System.out.print("[" + (i + 1) + "]");
            list[i].showInfoForCustomer();
        }
    }

    

    public boolean refillInventory(int index){
        if(list[index - 1].getQuantity() >= maxQuantity){
            System.out.println("Stock of this product is already full");
            return false;
        }
        list[index - 1].updateStock(maxQuantity);
        return true;
    }

    public Product findProduct(int index){
        return list[index - 1];
    }
    
    public Product createNewProduct(){
         if(counter >= MAX ){
            System.out.println("Max inventory reached");
            return null;
         }
         return new Product();
    }
}
