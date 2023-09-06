import java.util.Scanner;
import RetailSystemFiles.*;

public class Main {
    static Customer customer = new Customer();
    static Admin admin = new Admin();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        char op, x = 'X';
        do{
            op = logIn();
            switch(op){
                case '1':
                    x = customerMenu(); 
                    break;
                case '2':
                     x = adminMenu();
                    break;
                case 'X':
                    System.out.println("Goodbye!");
                    x = op;
                    break;
            }
        }
        while(x == 'W');
    }

    static char logIn(){
        char option;
        boolean isUser;
        do{
            isUser = false;
            System.out.println("\n----- USER LOGIN -----");
            System.out.println("[1] Customer");
            System.out.println("[2] Admin (UI in development)");
            System.out.println("[X] EXIT <--");
            System.out.print("    \noption > (number): ");
            option = scan.next().toUpperCase().charAt(0);
            switch(option){
                case '1' :
                    System.out.println("\n-----Customer logging in..-----\n");
                    System.out.println("Sign in");
                    //CustomerLogIn();
                    return option;
                case '2' :
                    System.out.println("\n-----Admin logging in..-----\n");
                    //AdminLogIn();
                    return option;
                case 'X' :
                    System.out.println("\nExiting program...");
                    return option;
                default :
                    System.out.println("Invalid Input");
            }
        }
        while(isUser == false);
        return option;
    }

    static void AdminLogIn(){

        System.out.print("AdminID: ");
        admin.setAdminId(scan.nextInt());

        System.out.print("Name: ");
        admin.setUserName(scan.next());
        
        System.out.print("EMAIL: ");
        admin.setEmail(scan.next());
        
        System.out.print("Department: ");
        admin.setDepartment(scan.next());
    }
    
    static void CustomerLogIn(){
        System.out.print("CustomerID: ");
        customer.setCustomerId(scan.nextInt());
        
        System.out.print("Name: ");
        customer.setUserName(scan.next());
    
        System.out.print("Address: ");
        customer.setAddress(scan.next());

        System.out.print("EMAIL: ");
        customer.setEmail(scan.next());
    }

    static char customerMenu(){
        char op;
        boolean x = false;
        do{
            System.out.println("\n-----CUSTOMER MENU-----");
            System.out.println("What would you like to do today " + customer.getName() + "?");
            System.out.println("[S] SHOP");
            System.out.println("[H] Order History");
            System.out.println("[W] Sign Out");
            System.out.print("      \noption > (number): ");
            op = scan.next().toUpperCase().charAt(0);
            switch(op){
                case 'S':
                    while(shop() == false);
                    break;
                case 'H':
                    customer.viewOrderHistory();
                    break;
                case 'W':
                    System.out.println("Signing you out...");
                    x = true;
                    break;
                default:
                    System.out.println("    Invalid input");
            }
        }
        while(x == false);
        return op;
    }

    static boolean shop(){
        boolean doneShopping;
        boolean doneChoosing;
        boolean isViewing;
        boolean validInput;
        char opt;
        int op;
        int qty;
        do{
            customer.resetTotal();
            customer.resetReceipt();
            customer.resetOrders();
            doneShopping = false;
            doneChoosing = false;
            opt = 'N';
            op = 0;
            qty = 0;
            char o;
            //choosing products
            do{
                isViewing = false;
                System.out.println("\n-----Products you can buy-----");
                validInput = true;
                admin.lookAtInventory(); //hahaha
                do{ // loop for choosing product menu
                    System.out.println("\n    Choose product to buy");
                    System.out.println("    [0] CHECKOUT --->");
                    System.out.println("    [C] View Cart");
                    System.out.println("    [X] Exit <---");
                    System.out.print("      option > (number): ");
                    o = scan.next().toUpperCase().charAt(0);
    
                    switch(o){
                        case '0':
                            customer.checkOut();
                            doneChoosing = true;
                            doneShopping = true;
                            break;
                        case 'X':
                            customer.resetReceipt();
                            doneShopping = true;
                            return doneShopping;
                        case 'C':
                            isViewing = true;
                            break;
                        default:
                            try{
                                op = Integer.parseInt(String.valueOf(o));
                            } catch (NumberFormatException nfe){
                                validInput = false;
                                System.out.println("invalid input");
                            }
                            if(op < 1 || op > admin.getInventoryCount() ){
                                validInput = false;
                                System.out.println("Invalid Input");
                            }
                    }
                    
                }
                while(validInput == false ); //checks if input is within range

                if(doneChoosing == true) break;

                Product p;

                if(isViewing == true){  //if customer wants to view cart
                    customer.viewCart();
                }
                else{   
                    p = admin.getProduct(op);
                    if(p.getQuantity() != 0){
                        //quantity choosing
                        do{
                            System.out.print("      input quantity: ");
                            qty = scan.nextInt();
                            if(qty > p.getQuantity()){
                                System.out.println("input exceeds our stock");
                            }
                        }
                        while(qty < 1 || qty > p.getQuantity());
                    }
                    else{
                        System.out.println("This product is no longer in stock");
                    }
                    if(p != null && p.getQuantity() > 0){
                            customer.placeOrder(p, qty);
                            admin.deductStock(op, qty);
                    }
                }
            }
            while(doneChoosing == false);
            System.out.print("\nDo You want to shop again? (Y/N) ");
            opt = scan.next().toUpperCase().charAt(0);
        
        }
        while(opt == 'Y');
        return doneShopping;
    }
    
    static char adminMenu(){
        boolean signedOut;
        char x;
        do{
            signedOut = false;
            System.out.println("\n-----ADMIN MENU-----");
            System.out.println("[1] Add Product");
            System.out.println("[2] Remove Product");
            System.out.println("[3] Manage Inventory");
            System.out.println("[W] Sign Out");
            x = scan.next().toUpperCase().charAt(0);
        
            switch(x){
                case '1':
                    addProductMenu();
                    break;
                case '2':
                    removeProductMenu();
                    break;
                case '3':
                    System.out.println("\n-----MANAGING INVENTORY-----");
                    admin.lookAtInventory();
                    break;
                case 'W':
                    signedOut = true;
                    System.out.println("Signing you out...");
                    break;
            }            
        }
        while(signedOut == false);
        return x;
    }
    
    static void addProductMenu(){
        Product p = admin.createProduct();
        if(p == null){
            return;
        }
        
        System.out.println("\n-----CREATING NEW PRODUCT");
        System.out.print("Enter Product ID: ");
        p.setProductId(scan.nextInt());
        
        System.out.print("Enter Product Name: ");
        p.setProductName(scan.next());
        
        System.out.print("Enter Price for " + p.getName() + ": ");
        p.updatePrice(scan.nextDouble());
        
        System.out.print("Enter quantity for " + p.getName() + ": ");
        p.updateStock(scan.nextInt());

        admin.addProduct(p);
        
        System.out.println("\n " + p.getName().toUpperCase() + " has been added to the inventory\n");
    }

    static void removeProductMenu(){
        char o;
        int op;
        boolean doneChoosing;
        boolean doneRemoving;
        do{
            doneChoosing = false;
            doneRemoving = false;
            op = -1;
            System.out.println("\n-----REMOVE A PRODUCT-----\n");
            admin.lookAtInventory();
            System.out.println("\n    Choose product to remove");
            System.out.println("    [X] Exit <---");
            System.out.print("      option > (number): ");
            o = scan.next().toUpperCase().charAt(0);
            switch(o){
                case 'X':
                    doneChoosing = true;
                    doneRemoving = true;
                    break;
                default:
                    try{ //check if user option can be an integer
                        op = Integer.parseInt(String.valueOf(o));
                    } catch (NumberFormatException nfe){
                        doneChoosing = false;
                        System.out.println("    invalid input");
                        break;
                    }
                    
                    //check if user option is within inventory range
                    if(op < 1 || op > admin.getInventoryCount() ){
                        doneChoosing = false;
                        System.out.println("    Invalid Input");
                    }
                    else doneChoosing = true;
            }
        }
        while(doneChoosing == false);

        if(doneRemoving == true) return;

        if(op == -1) {
            System.out.println("Failed to remove product");
        }
        else admin.removeProduct(op);
        System.out.println(admin.getInventoryCount());
        //get function for removing the product

    }
}

