package EntityManagers;

import Common.Admin;
import Common.Commons;
import Common.Rooms;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminManager {

    private TextFile_Manager fm;
    private Admin Admin[];
    private int SearchId;


    public AdminManager(String FileName) throws IOException {

        fm=new TextFile_Manager(FileName);
        fm.CreateTextFile();
        this.Array2Admin();
    }

    public void AddAdmin(Admin Admin) throws IOException {
        fm.AppendRow(Admin.toString());
        this.Array2Admin();
    }


    public void Array2Admin() throws FileNotFoundException {

        String A[]=fm.getArray();
        Admin=new Admin[fm.getRowCount()+100];
        for(int i=0;i<fm.getRowCount();i++){

            Admin[i]=new Admin();
            String B[]=A[i].split(Commons.Commons);

            Admin[i].SetName(B[0]);
            Admin[i].SetLastName(B[1]);
            Admin[i].SetNationalCode(B[2]);
            Admin[i].SetAge(Integer.parseInt(B[3]));
            Admin[i].SetGender(B[4]);
            Admin[i].SetPhoneNumber(B[5]);
            Admin[i].SetID(Integer.parseInt(B[6]));
            Admin[i].SetOwner(B[7]);
            Admin[i].SetPassword(B[8]);


        }

    }

    public String Search(int id) throws FileNotFoundException {

        String S="invalid";

        for (int i=0; i<fm.getRowCount();i++){
            if (Admin[i].GetID()==id){
                SearchId=i;
                S=Admin[i].toString();
            }
        }
        return S;


    }


    public void Delete(int a) throws IOException{

        Search(a);
        fm.delete_Rows(SearchId);
        this.Array2Admin();

    }

    public void Update(int a , Rooms roo) throws IOException{

        Search(a);
        fm.UpdateRow(SearchId , roo.toString() );
        this.Array2Admin();

    }

    public Admin[] GetArray(){
        return this.Admin;
    }
    public int GetRowCount() throws FileNotFoundException {
        return this.fm.getRowCount();
    }






}
