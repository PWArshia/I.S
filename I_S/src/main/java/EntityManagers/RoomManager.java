package EntityManagers;

import java.io.FileNotFoundException;
import java.io.IOException;

import Common.Commons;
import Common.Rooms;
import TxtFileManager.TextFile_Manager;

public class RoomManager {

    private TextFile_Manager fm;
    private Rooms rooms[];
    private int SearchId;

    public RoomManager(String FileName) throws IOException{
        fm=new TextFile_Manager(FileName);
        fm.CreateTextFile();
        this.Array2Rooms();
    }

    public void AddRoom(Rooms a) throws IOException{
        String S= a.GetNo()+ Commons.Commons + a.GetFloor()+Commons.Commons + a.GetRoomType()+ Commons.Commons + a.GetPrice() +Commons.Commons +a.GetIsBussy();
        fm.AppendRow(S);
        this.Array2Rooms();
    }

    public void Array2Rooms() throws IOException{
        
        String A[]=fm.getArray();
        rooms=new Rooms[fm.getRowCount()+100];
        for (int i=0; i<fm.getRowCount();i++){
            rooms[i]=new Rooms();
            String B[]=A[i].split(Commons.Commons);
            int No=Integer.parseInt(B[0]);
            int floor=Integer.parseInt(B[1]);
            Double price=Double.parseDouble(B[3]);
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

    public String search(int a) throws IOException{

        
        // for (int i=0; i<fm.getRowCount();i++){
        //     if (rooms[i].GetNo()==a){
        //         thisRoom.setNO(rooms[i].GetNo());
        //         thisRoom.SetFloor(rooms[i].GetFloor());
        //         thisRoom.SetRoomType(rooms[i].GetRoomType());
        //         thisRoom.SetPrice(rooms[i].GetPrice());
        //         thisRoom.SetIsBussy(rooms[i].GetIsBussy());
        //         SearchId=i;
        //         break;
        //     }
        // }

        String S="Invalid";
        for (int i=0; i<fm.getRowCount();i++){
            if (rooms[i].GetNo()==a){
                SearchId=i;
                S=rooms[i].toString();
            }
        }


        return S;
    }

    public void Delete(int a) throws IOException{

        search(a);
        fm.delete_Rows(SearchId);
        this.Array2Rooms();

    }

    public void Update(int a , Rooms roo) throws IOException{

        search(a);
        fm.UpdateRow(SearchId , roo.toString() );
        this.Array2Rooms();

    }

    public Rooms[] GetArray(){
        return this.rooms;
    }
    public int GetRowCount() throws FileNotFoundException {
        return this.fm.getRowCount();
    }


}
