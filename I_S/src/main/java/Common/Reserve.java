package Common;

import EntityManagers.RoomManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserve {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Members User;
    private Rooms Room;
    private int expire;
    private double price;

//    ------------------------------------------->cons

    public Reserve() {

        this.UpExpire();

    }

    public Reserve(int RoomId, Members member, RoomManager RoomManager, LocalDate startDate, LocalDate endDate , int a , int Id) throws IOException {

        this.SetStartDate(startDate);
        this.SetEndDate(a);
        this.SetUser(member);
        this.SetRoom(RoomId, RoomManager);
        this.expire = a;
        this.id = Id;
        this.SetPrice(a, this.Room);

    }

    @Override
    public String toString() {
        return this.GetId()+'$'+User.toString()+'$'+Room.toString()+'$'+startDate.toString()+'$'+endDate.toString()+'$'+this.GetExpire()+'$'+this.GetPrice();
    }

//    ------------------------------------------->Setter

    public boolean SetStartDate(LocalDate startDate) {

        if(startDate.isBefore(this.endDate)) {
            return false;
        }
        this.startDate = startDate;
        return true;
    }

    public boolean SetEndDate(int a) {
        if(a<=0){
            return false;
        }
        this.endDate= startDate.plusDays(a);
        return true;
    }

    public boolean SetEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return true;
    }

    public boolean SetUser(Members member) throws IOException {
        this.User = member;
        return true;
    }
    public boolean SetRoom(int a , RoomManager RoomManager) throws IOException {
        String s=RoomManager.search(a);
        this.Room=new Rooms(s);
        return true;
    }

    public boolean SetRoom(Rooms Room) throws IOException {
        this.Room=Room;
        return true;
    }

    public void UpExpire() {
        long temp= ChronoUnit.DAYS.between(this.startDate,this.endDate);
        if (temp>0) {
            this.expire = (int) temp;
        }
    }

    public void SetExpire(int a) {
        this.expire = a;
    }

    public void SetId(int a){
        id=a;
    }

    public boolean SetPrice(int a , Rooms rooms){

        if(a<=0){
            return false;
        }
        if(rooms.GetPrice()<=0){
            return false;
        }
        this.price=a * rooms.GetPrice();
        return true;
    }

    public boolean SetPrice(double price){

        if(price<=0){
            return false;
        }
        this.price=price;
        return true;
    }

//    ------------------------------------------------------------->Getter
    public String GetUser() {
        return User.toString();
    }
    public String GetRoom() {
        return Room.toString();
    }
    public LocalDate GetStartDate() {
        return startDate;
    }
    public LocalDate GetEndDate() {
        return endDate;
    }
    public int GetExpire() {
        return expire;
    }
    public int GetId() {
        return id;
    }
    public double GetPrice() {
        return price;
    }



}


