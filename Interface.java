import java.util.Scanner;
import RetailSystemFiles.*;

public class Interface {
    static Customer customer = new Customer();
    static Admin admin = new Admin();
    static Scanner scan = new Scanner(System.in);
    
    public void begin(){
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
                    x = op;
                    break;
            }
        }
        while(x == 'W');
    }

    public char logIn(){
        char option;
        boolean isUser;
        do{
            isUser = false;
            System.out.println("\n----- USER LOGIN -----");
            System.out.println("[1] Customer");
            System.out.println("[2] Admin");
            System.out.println("[X] EXIT <--");
            System.out.print("    \noption > (number): ");
            option = scan.next().toUpperCase().charAt(0);
            switch(option){
                case '1' :
                    System.out.println("\n-----Customer logging in..-----\n");
                    System.out.println("Sign in");
                    // CustomerLogIn();
                    customer.LogIn();
                    return option;
                case '2' :
                    System.out.println("\n-----Admin logging in..-----\n");
                    // AdminLogIn();
                    admin.LogIn();
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

    public void AdminLogIn(){

        System.out.print("AdminID: ");
        admin.setAdminId(scan.nextInt());

        System.out.print("Name: ");
        admin.setUserName(scan.next());
        
        System.out.print("EMAIL: ");
        admin.setEmail(scan.next());
        
        System.out.print("Department: ");
        admin.setDepartment(scan.next());
    }
    
    public void CustomerLogIn(){
        System.out.print("CustomerID: ");
        customer.setCustomerId(scan.nextInt());
        
        System.out.print("Name: ");
        customer.setUserName(scan.next());
    
        System.out.print("Address: ");
        customer.setAddress(scan.next());

        System.out.print("EMAIL: ");
        customer.setEmail(scan.next());
    }

    public char customerMenu(){
        char op;
        boolean x = false;
        do{
            System.out.println("\n-----CUSTOMER MENU-----");
            System.out.println("BALANCE: " + customer.getMoney());
            System.out.println("\nWhat would you like to do today " + customer.getName() + "?");
            System.out.println("[S] SHOP");
            System.out.println("[H] Order History");
            System.out.println("[W] Log Out");
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
                    customer.LogOut();
                    x = true;
                    break;
                default:
                    System.out.println("    Invalid input");
            }
        }
        while(x == false);
        return op;
    }

    public boolean shop(){
        if(admin.getInventoryCount() == 0){
            System.out.println("The shop is currently closed");
            return true;
        }
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
            customer.resetCart();
            customer.resetOrders();
            doneShopping = false;
            doneChoosing = false;
            opt = 'N';
            op = 0;
            qty = 0;
            char o;
            //choosing products
            do{
                customer.resetHasPurchaseHistory();
                customer.resetHasPurchased();
                isViewing = false;
                do{ // loop for choosing product menu
                    System.out.println("\n      -----Products you can buy-----");
                    System.out.println("BALANCE: " + customer.getMoney());
                    admin.lookAtInventoryForCustomer(); //hahaha
                    validInput = true;
                    System.out.println("\n    Choose product to buy");
                    System.out.println("    [0] CHECKOUT --->");
                    System.out.println("    [C] View Cart");
                    System.out.println("    [X] Exit <---");
                    System.out.print("      option > (number): ");
                    o = scan.next().toUpperCase().charAt(0);
    
                    switch(o){
                        case '0':
                            boolean x = checkOutMenu();
                            if(x == true){
                                if(customer.checkOut() == true){
                                    doneChoosing = true;
                                    doneShopping = true;
                                    admin.sellTheProduct();
                                    break;
                                }
                            }
                            validInput = false;
                            break;
                        case 'X':
                            if(customer.HasPurchased() == false && customer.HasPurchaseHistory() == false && customer.getCartStatus() == true){
                                admin.soldReset();
                            }
                            customer.resetReceipt();
                            customer.resetCart();
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
    
    public char adminMenu(){
        boolean signedOut;
        char x;
        do{
            signedOut = false;
            System.out.println("\n-----ADMIN MENU-----");
            System.out.println("[1] Add Product");
            System.out.println("[2] Remove Product");
            System.out.println("[3] Manage Inventory");
            System.out.println("[W] Log Out");
            System.out.print("      option > (number): ");
            x = scan.next().toUpperCase().charAt(0);
        
            switch(x){
                case '1':
                    addProductMenu();
                    break;
                case '2':
                    removeProductMenu(22);
                    break;
                case '3':
                    editProductMenu(23);
                    break;
                case 'W':
                    signedOut = true;
                    admin.LogOut();
                    break;
            }            
        }
        while(signedOut == false);
        return x;
    }
    public void addProductMenu(){
        Product p = admin.createProduct();
        if(p == null){
            return;
        }
        System.out.println("\n-----CREATING NEW PRODUCT");
        editProduct(p);
    }

    public void editProductMenu(int x){
        int op = chooseProductMenu(x);
        if(op > 0){
            modifyProduct(op);
        }
        else{
            if(op == -1) {
                System.out.println("Failed to remove product");
            }
        }
    }

    public void modifyProduct(int index){
        boolean doneChoosing;
        String name = admin.getProduct(index).getName();
        char x = manageMenu(name);
        doneChoosing = true;
        do{
            switch(x){
                case '1':              
                    System.out.print("Enter New Product ID: ");
                    admin.editProductId(scan.nextInt(), index);
                    break;
                case '2':
                    System.out.print("Enter new Product Name: ");
                    admin.editProductName(scan.next(), index);
                    break;
                case '3':
                    System.out.print("Enter new Price: ");
                    admin.editProductPrice(scan.nextDouble(), index);
                    break;
                case '4':
                    System.out.print("Enter new product Quantity");
                    admin.editProductQuantity(scan.nextInt(), index);
                    break;
                case 'X':
                    break;
                default:
                    doneChoosing = false;
            }
        }
        while(doneChoosing == false);
    }
    
    public void editProduct(Product p){
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

    public void removeProductMenu(int x){
        int op = chooseProductMenu(x);
        if(op > 0){
            admin.removeProduct(op);
        }
        else{
            if(op == -1) {
                System.out.println("Failed to remove product");
            }
        }
    }

    public int chooseProductMenu(int x){
        char o;
        int op;
        boolean doneChoosing;
        do{
            doneChoosing = false;
            // removeProductDialog();
            switch(x){
                case 22:
                    removeProductDialog();
                    break;
                case 23:
                    managingInventoryDialog();
                    break;
            }
            op = -1;
            o = scan.next().toUpperCase().charAt(0);
            switch(o){
                case 'X':
                    doneChoosing = true;
                    op = -2;
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

        return op;
    }

    public void removeProductDialog(){
        System.out.println("\n-----REMOVE A PRODUCT-----\n");
        admin.lookAtInventoryForAdmin();
        System.out.println("\n    Choose product to remove");
        System.out.println("    [X] Exit <---");
        System.out.print("      option > (number): ");
    }

    public void managingInventoryDialog(){
        System.out.println("\n-----MANAGING INVENTORY-----");
        admin.lookAtInventoryForAdmin();
        System.out.println("\n    Choose product to manage");
        System.out.println("    [X] Exit <---");
        System.out.print("      option > (number): ");
    }

    public char manageMenu(String name){
        char op;
        boolean doneChoosing;
        do{
            doneChoosing = false;
            System.out.println("\n-----CHOOSE WHAT TO EDIT FOR " + name.toUpperCase() + "----- ");
            System.out.println("[1] Product ID");
            System.out.println("[2] Product NAME");
            System.out.println("[3] Product PRICE");
            System.out.println("[4] Product QUANITY");
            System.out.println("[X] EXIT <--");
            System.out.print("      option > (number): ");
            op = scan.next().toUpperCase().charAt(0);
            switch(op){
                case '1':
                case '2':
                case '3':
                case '4':
                case 'X':
                    doneChoosing = true;
                    break;
                default:
                    System.out.println("    Invalid input");
            }
        }
        while(doneChoosing == false);
        return op;
    }

    public boolean checkOutMenu(){
        char op ;
        boolean isValid;
        do{
            isValid = true;
            // customer.viewCart();
            if(customer.viewCart() == false){
                return false;
            }

            boolean pay = payment();
            if(pay == false){
                return false;
            }
            
            System.out.print("\n        Confirm Check Out (y/n): ");
            op = scan.next().toUpperCase().charAt(0);
            if(op == 'Y'){
                return true;
            }
            else if( op == 'N'){
                return false;
            }
            else{
                System.out.println("        Invalid input");
                isValid = false;
            }
        }
        while(isValid == false);
        return true;
    }

    public boolean payment(){
        boolean validInput;
        String x;
        double p = 0;
        do{
            validInput = true;
            System.out.println("\n        [X] cancel");
            System.out.print("        Enter amount: ");
            x = scan.next().toUpperCase();
            if(x.equals("X")){
                return false;
            }
            else{
                try {
                    p = Double.parseDouble(x);
                } catch (NumberFormatException nfe) {
                    validInput = false;
                }
            }
        }
        while(validInput == false);
        return customer.pay(p);
    }
}
