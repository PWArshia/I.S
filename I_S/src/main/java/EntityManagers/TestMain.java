package EntityManagers;
import Common.Rooms;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        RoomManager manager = new RoomManager("Hello");

        Rooms A=new Rooms(1,false,1,"VIP",4000000.0);
        Rooms B=new Rooms(2,false,1,"normal",3000000.0);
        Rooms C=new Rooms(3,false,1,"normal",2500000.0);
        Rooms D=new Rooms(1,false,2,"VIP",3500000.0);
        Rooms E=new Rooms(2,false,2,"normal",1100000.0);

        manager.AddRoom(A);
        manager.AddRoom(B);
        manager.AddRoom(C);
        manager.AddRoom(D);
        manager.AddRoom(E);


    }

}
