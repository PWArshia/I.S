package EntityManagers;

import Common.AdminAction;
import Common.Commons;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AcManager {

    private TextFile_Manager fm;
    private AdminAction actions[];
    private int SearchId;


    public AcManager(String FileName) throws IOException {
        fm = new TextFile_Manager(FileName);
        fm.CreateTextFile();
//        this.Array2Ac():
    }

    public void AddAc(AdminAction action) throws IOException {
        fm.AppendRow(action.toString());
//        this.Array2Ac():
    }

    public void Array2Ac() throws FileNotFoundException {

        String Split[]=fm.getArray();
        actions = new AdminAction[fm.getRowCount()+100];

        for (int i = 0; i < fm.getRowCount(); i++) {
            actions[i] = new AdminAction(Split[i]);
        }

    }

    public String Search(int Code) throws FileNotFoundException {

        String Res="invalid";

        for (int i=0;i<fm.getRowCount();i++){
            if(actions[i].GetCode()==Code){
                Res=actions[i].toString();
                SearchId=i;
                return Res;
            }
        }
        return Res;

    }

    public AdminAction[] GetActions() {
        return this.actions;
    }

    public int GetRowCount() throws FileNotFoundException {
        return this.fm.getRowCount();
    }




}
