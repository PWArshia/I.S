package EntityManagers;

import Common.*;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Member;

public class ResserveManager {

    private TextFile_Manager fm;
    private Resserve resserve[];
    private int SearchId;
    private RoomManager roomManager;
    private MemberManager memberManager;

    public ResserveManager(String fileName) throws IOException {

        fm = new TextFile_Manager(fileName);
        fm.CreateTextFile();
//      this.Array2Resserve;

    }

    public void AddResserve(Resserve resserve) throws IOException {

        fm.AppendRow(resserve.toString());
//      this.Array2Resserve;
    }

    public void Array2Resserve() throws FileNotFoundException {

        String A[]= fm.getArray();
        resserve = new Resserve[fm.getRowCount()+100];

        for(int i=0; i<fm.getRowCount(); i++){
            resserve[i]=new Resserve();
            String B[]=A[i].split(Commons2.Commons);
            int id=Integer.parseInt(B[0]);
            String User[]=B[1].split(Commons.Commons);
            Members members=new Members(User[0],User[1],User[2],Integer.parseInt(User[3]),User[4],User[5],Integer.parseInt(User[6]));
            Rooms rooms=new Rooms(B[2]);
            








        }








    }



}
