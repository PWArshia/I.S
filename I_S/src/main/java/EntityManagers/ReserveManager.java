package EntityManagers;

import Common.*;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class ReserveManager {

    private TextFile_Manager fm;
    private Reserve reserve[];
    private int SearchId;
    private RoomManager roomManager;
    private MemberManager memberManager;

    public ReserveManager(String fileName) throws IOException {

        fm = new TextFile_Manager(fileName);
        fm.CreateTextFile();
        this.Array2Resserve();

    }

    public void AddResserve(Reserve reserve) throws IOException {

        fm.AppendRow(reserve.toString());
        this.Array2Resserve();
    }

    public void Array2Resserve() throws IOException {

        String A[]= fm.getArray();
        reserve = new Reserve[fm.getRowCount()+100];

        for(int i=0; i<fm.getRowCount(); i++){
            reserve[i]=new Reserve();
            String B[]=A[i].split(Commons2.Commons);
            int id=Integer.parseInt(B[0]);
            String User[]=B[1].split(Commons.Commons);
            Members members=new Members(User[0],User[1],User[2],Integer.parseInt(User[3]),User[4],User[5],Integer.parseInt(User[6]));
            Rooms rooms=new Rooms(B[2]);
            LocalDate start=LocalDate.parse(B[3]);
            LocalDate end=LocalDate.parse(B[4]);
            int ex=Integer.parseInt(B[5]);
            double price=Double.parseDouble(B[6]);


            reserve[i].SetRoom(rooms);
            reserve[i].SetUser(members);
            reserve[i].SetStartDate(start);
            reserve[i].SetEndDate(end);
            reserve[i].SetId(id);
            reserve[i].UpExpire();
            reserve[i].SetPrice(price);


        }
    }

    public String Search(int a) throws FileNotFoundException {
        String s="invalid user";
        for (int i=0; i<fm.getRowCount(); i++){
            if(reserve[i].GetId()==a){
                this.SearchId=i;
                s= reserve[i].toString();
                break;
            }
        }
        return s;
    }

    public void Delete(int a) throws IOException {

        this.Search(a);
        fm.delete_Rows(this.SearchId);
        this.Array2Resserve();

    }

    public void Update(int a, Reserve reserve) throws IOException {

        Search(a);
        fm.UpdateRow(this.SearchId , reserve.toString() );
        this.Array2Resserve();
    }

    public Reserve[] GetArray(){
        return this.reserve;
    }

    public int GetRowCount() throws FileNotFoundException {
        return this.fm.getRowCount();
    }



}
