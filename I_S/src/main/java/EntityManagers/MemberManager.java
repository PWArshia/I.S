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

}
