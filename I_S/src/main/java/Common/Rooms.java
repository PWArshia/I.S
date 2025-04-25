package Common;

public class Rooms {
    private int NO;
    private boolean IsBussy;
    private int floor;
    private enum RoomType{
        VIP,normal
    }
    private RoomType roomType;
    private int price;

    @Override
    public String toString(){
        return  this.NO+ Commons.Commons + this.floor+Commons.Commons + this.roomType+ Commons.Commons + this.price +Commons.Commons +this.IsBussy;
    }

   public Rooms(int No, boolean Is , int fl , String Type , int price){
     this.SetFloor(fl);
     this.SetIsBussy(Is);
     this.SetPrice(price);
     this.setNO(No);
     this.SetRoomType(Type);
   } 
    

    // ----------------------------------------------------------> Setter

    public void setNO(int a){
        this.NO=a;
    }

    public void SetFloor(int a){
        this.floor=a;
    }
    
    public void SetRoomType(String s){
        if (s.equals("VIP")){
            roomType=RoomType.VIP ;
        }
        else if(s.equals("NORMAL")){
            roomType=RoomType.normal;
        }
    }

    public void SetPrice(int a){
        this.price=a;
    }

    public void SetIsBussy(boolean a){
        this.IsBussy=a;
    }

    // ----------------------------------------------------------> Getter

    public int GetNo(){
        return this.NO;
    }

    public int GetFloor(){
        return this.floor;
    }

    public String GetRoomType(){
        String s="";

         if(roomType == roomType.VIP){
            s="VIP";
         }
         else{
            s="NORMAL";
         }

         return s;
    }

    public int GetPrice(){
        return this.price;
    }

    public boolean GetIsBussy(){
        return this.IsBussy;
    }

}
