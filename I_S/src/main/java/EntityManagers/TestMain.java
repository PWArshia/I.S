package EntityManagers;
import Common.*;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        Admin admin = new Admin("Kamyar" , "esmaeilpour" ,
                "0250561409" ,  19 , "male" , "09126700629",  52,  true , "kamyar123");

        AdminManager adminManager=new AdminManager("AdminList");
        adminManager.AddAdmin(admin);
    }

}
