package Common;

import EntityManagers.RoomManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserve {

   private int id;
   private LocalDate startDate;
   private LocalDate endDate;
   private double totalPrice;
   private Rooms room;
   private Members member;
   private String status;

//----------------------------------------------->cons
   public Reserve(int id, Rooms room, Members member, LocalDate startDate, LocalDate endDate,String status) {

      this.Setid(id);
      this.Setroom(room);
      this.Setuser(member);
      this.SetstartDate(startDate);
      this.SetendDate(endDate);
      this.SettotalPrice();
      this.status=status;

   }

   public Reserve(String s){
      String split[]=s.split(Commons.Commons);
      this.Setid(Integer.parseInt(split[0]));
      this.room = new Rooms();
      this.room.setNO(Integer.parseInt(split[1]));
      this.member = new Members();
      this.member.SetID(Integer.parseInt(split[2]));
      this.SetstartDate(LocalDate.parse(split[3]));
      this.SetendDate(LocalDate.parse(split[4]));
      this.SettotalPrice();
      this.status=split[6];

   }


   public Reserve(String s , Rooms roo, Members mm){
      String split[]=s.split(Commons.Commons);
      this.Setid(Integer.parseInt(split[0]));
      this.room = roo;
      this.member = mm;
      this.SetstartDate(LocalDate.parse(split[3]));
      this.SetendDate(LocalDate.parse(split[4]));
      this.SettotalPrice();
      this.status=split[6];

   }

   @Override
   public String toString() {
      return this.Getid()+"&"+this.Getroom().GetNo()+"&"+this.Getuser().GetID()+"&"+this.GetstartDate()+"&"+this.GetendDate()+"&"+this.GettotalPrice()+"&"+this.GettotalPrice()+this.Getstatus();
   }




//------------------------------------------------------>Setter

   public boolean Setid(int id) {
      if (id<=0){
         return false;
      }
      this.id = id;
      return true;
   }

   public boolean Setroom(Rooms room) {
      this.room = room;
      return true;
   }


   public boolean Setuser(Members member) {
      this.member= member;
      return true;
   }

   public boolean SetstartDate(LocalDate startDate) {

      if(startDate.isBefore(LocalDate.now())){
         return false;
      }
      this.startDate = startDate;
      return true;
   }
   public boolean SetendDate(LocalDate endDate) {

      if(endDate.isBefore(LocalDate.now())){
         return false;
      }
      if(this.startDate.isAfter(endDate)){
         return false;
      }
      this.endDate = endDate;
      return true;
   }
   public boolean SettotalPrice() {
      double price  =ChronoUnit.DAYS.between(this.startDate, this.endDate) * this.room.GetPrice();
      if(price<=0){
         return false;
      }
      this.totalPrice = price;
      return true;
   }
   public boolean SetStatus(String status) {
      this.status = status;
      return true;
   }

//   -------------------------------------------------------------------->Getter


   public int Getid() {
      return this.id;
   }
   public Rooms Getroom() {
      return this.room;
   }
   public Members Getuser() {
      return this.member;
   }
   public LocalDate GetstartDate() {
      return this.startDate;
   }
   public LocalDate GetendDate() {
      return this.endDate;
   }
   public double GettotalPrice() {
      return this.totalPrice;
   }
   public String Getstatus() {
      return this.status;
   }






}


