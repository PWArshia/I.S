package Common;

public class Rooms {
    private int NO;
    private boolean IsBussy;
    private int floor;
    private enum RoomType{
        VIP,normal
    }
    private RoomType roomType;
    private Double price;


//    ------------------------------------------------------------->Cons

    @Override
    public String toString(){
        return  this.NO+ Commons.Commons + this.floor+Commons.Commons + this.roomType+ Commons.Commons + this.price +Commons.Commons +this.IsBussy;
    }

    public Rooms(){}

   public Rooms(int No, boolean Is , int fl , String Type , Double price){
     this.SetFloor(fl);
     this.SetIsBussy(Is);
     this.SetPrice(price);
     this.setNO(No);
     this.SetRoomType(Type);
   } 
    


    // ----------------------------------------------------------> Setter

    public boolean setNO(int a){
        if (a>=0){
            this.NO=a;
            return true;
        }
        return false;
    }

    public boolean SetFloor(int a){
        if (a>=0){
            this.floor=a;
            return true;
        }
        return false;
    }
    
    public boolean SetRoomType(String s){
        if (s.equals("VIP")){
            roomType=RoomType.VIP ;
            return true;
        }
        else if(s.equals("NORMAL")){
            roomType=RoomType.normal;
            return true;
        }
        return false;
    }

    public boolean SetPrice(Double a){
        if (a>0){
            this.price=a;
            return true;
        }
        return false;
    }

    public boolean SetIsBussy(boolean a){
        this.IsBussy=a;
        return true;
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

    public Double GetPrice(){
        return this.price;
    }

    public boolean GetIsBussy(){
        return this.IsBussy;
    }

}
