package EntityManagers;

import Common.Commons;
import Common.Members;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MemberManager {
    private TextFile_Manager MembersFileManager;
    private Members[] members=new Members[1000];
    private int cmembers=0;

    public MemberManager(String FileName) throws IOException {
        MembersFileManager = new TextFile_Manager(FileName);
        MembersFileManager.CreateTextFile();
        TextFile2Array();
    }

    private void TextFile2Array() throws FileNotFoundException {
        String[] A = MembersFileManager.getArray();
        int cA=MembersFileManager.getRowCount();
        for (int i = 0; i < cA; i++) {
            String[] B = A[i].split(Commons.Commons);

            members[i]=new Members(B[0],B[1],B[2],Integer.parseInt(B[3]),B[4],B[5],Integer.parseInt(B[6]));

       }
        cmembers=cA;
    }

    private void Array2TextFile() throws IOException {
        MembersFileManager.ClearTextFile();
        for (int i = 0; i < cmembers; i++) {
            MembersFileManager.AppendRow(members[i].toString());
        }
    }


    public void AddMember(Members Member) throws IOException {
        MembersFileManager.AppendRow(Member.toString());
        TextFile2Array();



//        members[cmembers++]=Member;
//        Array2TextFile();
    }

    public boolean DeleteMember(Members M) throws IOException {


        int a=IndexSearchMember(M.GetID());
        if (a==-1)
            return false;
        MembersFileManager.delete_Rows(a);
        TextFile2Array();
        return true;


//        this.SearchMember(a);
//        MembersFileManager.delete_Rows(this.SearchId);
//        this.TextFile2Array();

    }



    public void UpdateMember(Members M,Members M1) throws IOException {
        int a=IndexSearchMember(M.GetID());
        MembersFileManager.UpdateRow(a,M1.toString());
        TextFile2Array();




//        members[a]=M;
//        Array2TextFile();
    }





    public String SearchMember(int ID) throws IOException {

        for (int i = 0; i < cmembers; i++) {
            if (this.members[i].GetID()==ID) {
                return members[i].toString();
            }
        }
        return null;

    }



    public int IndexSearchMember(int ID) throws IOException {
        for (int i = 0; i < cmembers; i++) {
            if (this.members[i].GetID()==ID) {
                return i;
            }
        }
        return -1;
    }



    public boolean SearchNationalCode(String NCOD) throws IOException {
        for (int i = 0; i < cmembers; i++) {
            if (this.members[i].GetNationalCode().equals(NCOD)) {
                return true;
            }
        }
        return false;
    }






//    public String SearchMember(String NCOD) throws IOException {
//        for (int i = 0; i < cmembers; i++) {
//            if (members[i].GetNationalCode().equals(NCOD)) {
//                this.SearchId=i;
//                return members[i].toString();
//            }
//        }
//        return null;
//    }


    public void SetArray(Members[] M,int cM) throws IOException {
        members=M;
        cmembers=cM;
        Array2TextFile();
    }
    public Members[] GetArray() {
        return members;
    }
    public int GetLengthArray() {
        return cmembers;
    }


}
