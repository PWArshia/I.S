package Common;

import EntityManagers.RoomManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserve {

   private int id;
   private int roomNo;
   private int userId;
   private LocalDate startDate;
   private LocalDate endDate;
   private double totalPrice;

//----------------------------------------------->cons
   public Reserve(int id, Rooms room, Members member, LocalDate startDate, LocalDate endDate) {

      this.Setid(id);
      this.SetroomNo(room);
      this.SetuserId(member);
      this.SetstartDate(startDate);
      this.SetendDate(endDate);
      this.SettotalPrice(room);

   }

   public Reserve(String s){

      String split[]=s.split(Commons.Commons);

      this.Setid(Integer.parseInt(split[0]));
      this.SetroomNo(Integer.parseInt(split[1]));
      this.SetuserId(Integer.parseInt(split[2]));
      this.SetstartDate(LocalDate.parse(split[3]));
      this.SetendDate(LocalDate.parse(split[4]));
      this.SettotalPrice(Integer.parseInt(split[5]));

   }


   @Override
   public String toString() {
      return this.Getid()+"&"+this.GetroomNo()+"&"+this.GetuserId()+"&"+this.GetstartDate()+"&"+this.GetendDate()+"&"+this.GettotalPrice();
   }

//------------------------------------------------------>Setter

   public boolean Setid(int id) {
      if (id<=0){
         return false;
      }
      this.id = id;
      return true;
   }
   public boolean SetroomNo(Rooms room) {
      this.roomNo = room.GetNo();
      return true;
   }
   public boolean SetroomNo(int a) {
      if (a<=0){
         return false;
      }
      this.roomNo = a;
      return true;
   }
   public boolean SetuserId(Members member) {
      this.userId= member.GetID();
      return true;
   }
   public boolean SetuserId(int a) {
      if (a<=0){
         return false;
      }
      this.userId= a;
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
   public boolean SettotalPrice(Rooms room) {
      double price  =ChronoUnit.DAYS.between(startDate, endDate) * room.GetPrice();
      if(price<=0){
         return false;
      }
      this.totalPrice = price;
      return true;
   }
   public boolean SettotalPrice(int a) {
      if(this.startDate.isAfter(endDate)){
         return false;
      }
      this.totalPrice = a;
      return true;
   }
//   -------------------------------------------------------------------->Getter


   public int Getid() {
      return this.id;
   }
   public int GetroomNo() {
      return this.roomNo;
   }
   public int GetuserId() {
      return this.userId;
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






}


