package EntityManagers;

import Common.*;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class ReserveManager {

    private TextFile_Manager fm ;
    private MemberManager M_manager ;
    private RoomManager R_manager ;
    private Reserve res[];
    private int SearchId;


    public ReserveManager(String FileName , MemberManager m_manager , RoomManager r_manager) throws IOException {

        fm = new TextFile_Manager(FileName);
        M_manager = m_manager;
        R_manager = r_manager;
        fm.CreateTextFile();
        this.Array2Res();
    }

    public void Array2Res() throws IOException {

        String A[]= fm.getArray();
        this.res = new Reserve[fm.getRowCount()+100];

        for (int i = 0; i < fm.getRowCount(); i++) {

            String split[]=A[i].split(Commons.Commons);
            String temp1=R_manager.search(Integer.parseInt(split[1]));
            Rooms roo=new Rooms(temp1);
            String temp2 = M_manager.SearchMember(Integer.parseInt(split[2]));
            String[] B = temp2.split(Commons.Commons);
            Members members=new Members(B[0],B[1],B[2],Integer.parseInt(B[3]),B[4],B[5],Integer.parseInt(B[6]));
            this.res[i]=new Reserve(A[i] , roo, members);
        }


    }


    public void updateExpiredReservations() throws IOException {
        LocalDate today = LocalDate.now();
        boolean updated = false;
        for (int i = 0; i < fm.getRowCount(); i++) {
            Reserve res1 = this.res[i];
            if (res1.Getstatus().equals("CONFIRMED") && !res1.GetendDate().isAfter(today)) {
                // رزرو منقضی شده است
                Rooms room = res1.Getroom();
                room.SetIsBussy(false);
                R_manager.Update(room.GetNo(), room);
                res1.SetStatus("EXPIRED");
                fm.UpdateRow(i, res.toString());
                updated = true;
            }
        }
        if (updated) {
            this.Array2Res();
        }
    }




    public boolean AddRes(Reserve r) throws IOException {

        if (!isRoomAvailable(r.Getroom().GetNo(), r.GetstartDate(), r.GetendDate())) {
            return false;
        }
        fm.AppendRow(r.toString());
        Rooms room = r.Getroom();
        room.SetIsBussy(true);
        R_manager.Update(room.GetNo(), room);
        Array2Res();
        return true;
    }
    public boolean isRoomAvailable(int roomNo, LocalDate checkIn, LocalDate checkOut) throws IOException {
        Array2Res();
        for (int i = 0; i < fm.getRowCount(); i++) {
            Reserve res = this.res[i];
            if (res.Getroom().GetNo() == roomNo && res.Getstatus().equals("CONFIRMED") &&
                    !(checkOut.isBefore(res.GetstartDate()) || checkIn.isAfter(res.GetendDate()))) {
                return false;
            }
        }
        return true;
    }
    public String Search(int a) throws FileNotFoundException {

        String Res="invalid";

        for (int i=0;i<fm.getRowCount();i++){
            if(res[i].Getid()==a){
                Res=res[i].toString();
                SearchId=i;
                return Res;
            }
        }
        return Res;


    }

    public boolean delete(int id) throws IOException {

        String temp=this.Search(id);
        if (temp.equals("invalid")) {
            return false;
        }
        fm.delete_Rows(this.SearchId);
        Rooms room=res[SearchId].Getroom();
        room.SetIsBussy(false);
        R_manager.Update(room.GetNo(), room);
        Array2Res();
        return true;


    }


    public Reserve[] GetReserve() {
        return this.res;
    }

    public int GetRowCount() throws FileNotFoundException {
        return this.fm.getRowCount();
    }


}
