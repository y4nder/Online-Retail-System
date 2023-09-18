package RetailSystemFiles;
public abstract class User{
   protected int userId;
   protected String userName;
   protected String email;
      
   // setters
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

   // abstract methods
   public abstract void LogIn();
   
   public abstract void LogOut(); 
}