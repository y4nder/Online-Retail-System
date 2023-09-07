package RetailSystemFiles;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
public class Order {
    private DateFormat date;
    private Date obj = new Date();
    private int orderId;
    private int customerId;
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
        customerId = 0;
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

        counter++;
        productList[counter-1] = p;
        calculateTotalAmount(productList[counter-1], quantity);
        addToReceipt(productList[counter-1], quantity);
        return true;
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

    public void confirmOrder(){
        orderId++;
        customerId++;
        System.out.println(receipt);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Purchase succesfull!");
        System.out.println("You paid $" + totalAmount);
        System.out.println("Date: " + orderDate);
    }

    public void displayOrders(){
        // System.out.println("-----Displaying Orders-----");
        for(int i = 0; i < counter; i++){
            System.out.println(productList[i].getProductId() + "   " 
                + productList[i].getName() + " $" + productList[i].getPrice());
        }
    }
}
