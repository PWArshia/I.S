package EntityManagers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Spliterator;

import Common.Commons;
import Common.Rooms;
import TxtFileManager.TextFile_Manager;

public class RoomManager {

    private TextFile_Manager fm;
    private Rooms rooms[];
    private Rooms thisRoom;

    public RoomManager() throws IOException{
        fm=new TextFile_Manager("Room");
        fm.CreateTextFile();
    }

    public void AddRoom(Rooms a) throws IOException{
        String S= a.GetNo()+ Commons.Commons + a.GetFloor()+Commons.Commons + a.GetRoomType()+ Commons.Commons + a.GetPrice() +Commons.Commons +a.GetIsBussy();

        fm.AppendRow(S);
    }

    public void Array2books() throws IOException{
        
        String A[]=fm.getArray();
        rooms=new Rooms[1000];
        for (int i=0; i<fm.getRowCount();i++){
            String B[]=A[i].split(Commons.Commons);
            int No=Integer.parseInt(B[0]);
            int floor=Integer.parseInt(B[1]);
            int price=Integer.parseInt(B[3]);
            boolean Bussy=true;
            if (B[4].equals(false)){
                Bussy=false;
            }

            rooms[i].setNO(No);
            rooms[i].SetFloor(floor);
            rooms[i].SetRoomType(B[2]);
            rooms[i].SetPrice(price);
            rooms[i].SetIsBussy(Bussy);
        }
    }

    public void search(int a) throws IOException{

        for (int i=0; i<fm.getRowCount();i++){
            if (rooms[i].GetNo()==a){
                thisRoom.setNO(rooms[i].GetNo());
                thisRoom.SetFloor(rooms[i].GetFloor());
                thisRoom.SetRoomType(rooms[i].GetRoomType());
                thisRoom.SetPrice(rooms[i].GetPrice());
                thisRoom.SetIsBussy(rooms[i].GetIsBussy());
                break;
            }
        }
    }



}
