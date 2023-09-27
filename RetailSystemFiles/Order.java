package RetailSystemFiles;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
public class Order {
    private int orderId;
    private Product[] orders;
    private int MAX;
    private int counter;
    private double totalAmount;
    private String receipt;
    private String orderDate;
    private DateFormat date;
    private Date obj = new Date();

    public Order(){
        date = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        obj = new Date();
        orderId = 0;
        MAX = 99;
        orders = new Product[MAX];
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
        orders = new Product[MAX];
        counter = 0;
    }


    //methods
    public void calculateTotalAmount(Product p, int qty){
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

        //checking of duplicate products
        int index = checkDuplicate(p, quantity);
        if (index  != -1){ 
            p.sell(quantity);
            orders[index].updateStock(orders[index].getQuantity() + quantity);
            calculateThenAddtoReceipt(orders[index], quantity);
        }
        else{
            counter++;
            p.sell(quantity);
            orders[counter-1] = new Product(p.getProductId(), p.getName(), p.getPrice(), quantity);
            calculateThenAddtoReceipt(orders[counter-1], quantity);

        }   

        return true;
    }

    private void calculateThenAddtoReceipt(Product p, int quantity){
        calculateTotalAmount(p, quantity);
        addToReceipt(p, quantity);
    }

    private int checkDuplicate(Product p, int quantity){
        for(int i = 0; i < counter; i++){
            if(orders[i].getProductId() == p.getProductId()){
                return i;
            }
        }
        return -1;
    }

    
    public boolean removeProductToOrder(int index){
        if(counter == 0){
            System.out.println("        YOUR CART IS EMPTY :<");
            return false;
        }
        
        System.out.println(orders[index-1].getName() + " has been removed");
        for(int i = index - 1; i < counter - 1; i++){
            orders[i] = orders[i + 1];
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

    private void addToReceipt(Product p, int qty){
        receipt += "         $" + p.getPrice() + "      " + p.getName() + "      x" + qty + "\n"; 
    }

    public void displayOrders(){
        for(int i = 0; i < counter; i++){
            System.out.printf("%15s %15s %15s\n",orders[i].getName(), "$" + orders[i].getPrice(), "   x" + orders[i].getQuantity());
        }
    }
}
