package EntityManagers;

import Common.Members;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        MemberManager manager = new MemberManager("Hello");
        Members K=new Members("kamy","esmaeilpour","0250561409",
                19,"Male","09126700629");
        Members A=new Members("Arshia","Sedghi","4123547812",
                20,"Male","09916730659");
        Members k=new Members("Kasra","Masoudi","9345812358",
                20,"male","45631852365");

        manager.AddMember(K);
        manager.AddMember(A);
        manager.AddMember(k);


        System.out.println(manager.SearchMember(A));
    }

}
