package RetailSystemFiles;
public class User{
   protected int userId;
   protected String userName;
   protected String email;
   
   //constructors
   public User(int usId, String usName, String em){
      this.userId = usId;
      this.userName = usName;
      this.email = em;
   }
   
   public User(){
      this.userId = 0;
      this.userName = "";
      this.email = "";
   }
   
   //setters
   public void setUserId(int id){
      userId = id;
   }
   
   public void setUserName(String name){
      userName = name;
   }
   
   public void setEmail(String email){
      this.email = email;
   }

   //getters
   public String getName(){
      return userName;
   }

   public String getUserName(){
      return userName;
   }

   public String getEmail(){
      return email;
   }

   //methods
   public void LogIn(){
      System.out.println(getName() + "is logged in");
   }
   
   public void LogOut(){
      System.out.println(getName() + "is logged out");
   }

   public String toString(){
      return "user ID: " + userId + "\nusername: " + userName + " \nemail: " + email;
   }
}