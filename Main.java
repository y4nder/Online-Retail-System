import java.util.Scanner;
import RetailSystemFiles.*;

public class Main {
    static Customer customer = new Customer();
    static Admin admin = new Admin();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        char op = logIn();
        switch(op){
            case '1':
                customerMenu();            
            case '2':
                adminMenu();
        }
    }

    static char logIn(){
        char option;
        boolean isUser;
        do{
            isUser = false;
            System.out.println("----- USER LOGIN -----");
            System.out.println("[1] Customer");
            System.out.println("[2] Admin (UI in development)");
            System.out.print("    \noption > (number): ");
            option = scan.nextLine().charAt(0);
            switch(option){
                case '1' :
                    System.out.println("\nCustomer logging in..\n");
                    System.out.println("Sign in");
                    //CustomerLogIn();
                    isUser = true;
                    return option;
                case '2' :
                    System.out.println("\nAdmin logging in..\n");
                    AdminLogIn();
                    isUser = true;
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

    static void customerMenu(){
        char op;
        boolean x = false;
        do{
            System.out.println("\n-----CUSTOMER MENU-----");
            System.out.println("What would you like to do today " + customer.getName() + "?");
            System.out.println("[S] SHOP");
            System.out.println("[X] exit <--");
            System.out.print("      \noption > (number): ");
            op = scan.next().toUpperCase().charAt(0);
            switch(op){
                case 'S':
                    while(shop() == false);
                    break;
                case 'X':
                    return;
                default:
                    System.out.println("    Invalid input");
                    x = true;
            }
        }
        while(x == false);
        
    }

    static boolean shop(){
        boolean doneShopping = false;
        boolean isViewing = false;
        char opt;
        do{
            customer.resetTotal();
            customer.clearReceipt();
            boolean isDone = false;
            do{
                System.out.println("\n-----Products you can buy-----");
                admin.lookAtInventory(); //hahaha
                System.out.println("[0] CHECKOUT --->");
                System.out.println("[C] View Cart");
                System.out.println("[X] Exit <---");
                String x;
                int op;
                int qty = 0;
                
                //choosing products
                do{
                    System.out.println("\n    Choose product to buy");
                    System.out.print("      option > (number): ");
                    x = scan.next();
    
                    if( x.equalsIgnoreCase("0")){
                        customer.checkOut();
                        isDone = true;
                        doneShopping = true;
                        op = 0;
                        break;
                    }
                    
                    if( x.equalsIgnoreCase("X")){ // for check out
                        customer.clearReceipt();
                        return doneShopping;
                    }
                    
                    if( x.equalsIgnoreCase("C")){
                        op = 0;
                        isViewing = true;
                        break;
                    }

                    op = Integer.parseInt(x);
    
                }
                while( op < 0 || op > admin.getInventoryCount());
                
                if(isDone == true ){ 
                    break; 
                }
                
                Product p;
                
                if(isViewing == true){
                    customer.viewOrderHistory();   
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
            while(isDone == false);
            System.out.print("Do You want to shop again? (Y/N) ");
            opt = scan.next().toUpperCase().charAt(0);

        }
        while(opt == 'Y');
        return doneShopping;
    }



    static void adminMenu(){

    }
}
