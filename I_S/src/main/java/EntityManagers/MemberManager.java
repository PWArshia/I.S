package EntityManagers;

import Common.Commons;
import Common.Members;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MemberManager {
    private TextFile_Manager MemberManager;
    private Members[] MemberS=new Members[1000];
    private int cMemberS=0;

    public MemberManager(String FileName) throws IOException {
        MemberManager = new TextFile_Manager(FileName);
        MemberManager.CreateTextFile();
    }

    private void TextFile2Array() throws FileNotFoundException {
        String[] A = MemberManager.getArray();
        int cA=MemberManager.getRowCount();
        for (int i = 0; i < cA; i++) {
            String[] B = A[i].split(Commons.Commons);

            MemberS[i]=new Members(B[0],B[1],B[2],Integer.parseInt(B[3]),B[4],B[5]);


        }
        cMemberS=cA;
    }

    private void Array2TextFile() throws IOException {
        MemberManager.ClearTextFile();
        for (int i = 0; i < cMemberS; i++) {
            MemberManager.AppendRow(MemberS[i].toString());
        }
    }


    public void AddMember(Members Member) throws IOException {
        MemberS[cMemberS++]=Member;
        Array2TextFile();
    }

    public void DeleteMember(int a) throws IOException {
        for (int i = a; i <= cMemberS; i++) {
            MemberS[i]=MemberS[i+1];
        }
        cMemberS--;
        Array2TextFile();
    }
    public void DeleteMember(Members Member) throws IOException {
        for (int i = 0; i < cMemberS; i++) {
            if (MemberS[i].equals(Member)) {
                DeleteMember(i);
                return;
            }
        }
    }


    public void UpdateMember(int a,Members M) throws IOException {
        MemberS[a]=M;
        Array2TextFile();
    }


    public String SearchMember(Members Member) throws IOException {
        for (int i = 0; i < cMemberS; i++) {
            if (MemberS[i].equals(Member)) {
                return MemberS[i].toString();
            }
        }
        return null;
    }


    public void SetArray(Members[] M,int cM) {
        MemberS=M;
        cMemberS=cM;
    }
    public Members[] GetArray() {
        return MemberS;
    }
    public int GetLengthArray() {
        return cMemberS;
    }


}
