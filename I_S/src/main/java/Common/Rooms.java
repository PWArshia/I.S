package Common;

public class Rooms {
    private int NO;
    private boolean IsBussy;
    private int floor;
    private enum RoomType{
        VIP,normal
    }
    private RoomType roomType;
    private Float price;

    @Override
    public String toString(){
        return  this.NO+ Commons.Commons + this.floor+Commons.Commons + this.roomType+ Commons.Commons + this.price +Commons.Commons +this.IsBussy;
    }

   public Rooms(int No, boolean Is , int fl , String Type , Float price){
     this.SetFloor(fl);
     this.SetIsBussy(Is);
     this.SetPrice(price);
     this.setNO(No);
     this.SetRoomType(Type);
   } 
    

    // ----------------------------------------------------------> Setter

    public void setNO(int a){
        if (a>=0){
            this.floor=a;
        }
    }

    public void SetFloor(int a){
        if (a>=0){
            this.floor=a;
        }
    }
    
    public void SetRoomType(String s){
        if (s.equals("VIP")){
            roomType=RoomType.VIP ;
        }
        else if(s.equals("NORMAL")){
            roomType=RoomType.normal;
        }
    }

    public void SetPrice(Float a){
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

    public Float GetPrice(){
        return this.price;
    }

    public boolean GetIsBussy(){
        return this.IsBussy;
    }

}
