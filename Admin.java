public class Admin extends User{
   protected int adminId;
   protected String department;
   
   public Admin(int usId, String usName, String em, int adId, String dep){
      super(usId, usName, em);
      adminId = adId,
      department = dep;
   }
   
   public Admin(){
      super(usId, usName, em);
      adminId = 00,
      department = "";
   }
   
   public String toString(){
      return super.toString() + "\n admin id: " + adminId + "\n department: " + department;
   }
}