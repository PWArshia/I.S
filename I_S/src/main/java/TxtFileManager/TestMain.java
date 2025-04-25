package TxtFileManager;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        TextFile_Manager A=new TextFile_Manager("Hello");
        A.CreateTextFile();
        System.out.println(A.getAddress());
        A.AppendRow("salam");
        A.AppendRow("bedrood");
        A.AppendRow("Kamy");
        A.UpdateRow(2,"a");
    }
}
