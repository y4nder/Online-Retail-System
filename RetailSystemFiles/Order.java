package RetailSystemFiles;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
public class Order {
    private DateFormat date;
    private Date obj = new Date();
    private int orderId;
    private int MAX;
    private Product[] productList;
    private int counter;
    private double totalAmount;
    private String receipt;
    private String orderDate;

    public Order(){
        date = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        obj = new Date();
        orderId = 0;
        MAX = 20;
        productList = new Product[MAX];
        totalAmount = 0;
        orderDate = date.format(obj);
        receipt = "";
        counter = 0;
    }

    //getters
    public int getCountOrder(){
        return counter;
    }

    public String getReceipt(){
        return receipt;
    }

    public String getDate(){
        setDate();
        return orderDate;
    }

    public double getTotal(){
        return totalAmount;
    }

    //setters
    public void resetTotal(){
        totalAmount = 0;
    }

    public void clearR(){
        receipt = "";
    }

    public void setDate(){
        orderDate = date.format(obj);
    }

    public void clearCart(){
        productList = new Product[MAX];
        counter = 0;
    }


    //methods
    public void calculateTotalAmount(Product p, int qty){
        // for(int i = 0; i < counter; i++){
        //     totalAmount += productList[i].getPrice();
        // }
        // return totalAmount;
        totalAmount += p.getPrice() * qty;
    }

    public boolean addProductToOrder(Product p, int quantity){
        
        if(counter >= MAX){
            System.out.println("Maximum limit of Orders reached");
            return false;
        }
        
        if(p.getQuantity() < quantity){
            System.out.println("Quantity of " + p.getName() + " is: " + p.getQuantity());
            return false;
        }

        int index;
        if( (index = checkDuplicate(p, quantity) ) != -1){ //algorithm to check for duplicates
            p.sell(quantity);
            System.out.println(p.getSold());
            productList[index].updateStock(productList[index].getQuantity() + quantity);
            calculateTotalAmount(productList[index], quantity);
            addToReceipt(productList[index], quantity);
        }
        else{
            counter++;
            p.sell(quantity);
            System.out.println(p.getSold());
            productList[counter-1] = new Product(p.getProductId(), p.getName(), p.getPrice(), quantity);
            calculateTotalAmount(productList[counter-1], quantity);
            addToReceipt(productList[counter-1], quantity);
        }   

        return true;
    }

    private int checkDuplicate(Product p, int quantity){
        for(int i = 0; i < counter; i++){
            if(productList[i].getProductId() == p.getProductId()){
                System.out.println("Duplicate found");
                return i;
            }
        }
        return -1;
    }

    private void addToReceipt(Product p, int qty){
        receipt += "         $" + p.getPrice() + "      " + p.getName() + "      x" + qty + "\n"; 
    }

    public boolean removeProductToOrder(int index){
        if(counter == 0){
            System.out.println("        YOUR CART IS EMPTY :<");
            return false;
        }

        System.out.println(productList[index-1].getName() + " has been removed");
        for(int i = index - 1; i < counter - 1; i++){
            productList[i] = productList[i + 1];
        }
        counter--;
        return true;
    }

    public void confirmOrder(int id){
        orderId++;
        System.out.println(receipt);
        System.out.println("Customer ID: " + id);
        System.out.println("Order ID: " + orderId);
        System.out.println("Purchase succesfull!");
        System.out.println("You paid $" + totalAmount);
        System.out.println("Date: " + orderDate);
    }

    public void displayOrders(){
        for(int i = 0; i < counter; i++){
            System.out.printf("%15s %15s %15s\n",productList[i].getName(), "$" + productList[i].getPrice(), "   x" + productList[i].getQuantity());
        }
    }
}
