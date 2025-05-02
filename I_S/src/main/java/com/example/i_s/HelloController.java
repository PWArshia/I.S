package com.example.i_s;

import Common.Admin;
import Common.Rooms;
import EntityManagers.AdminManager;
import EntityManagers.HotelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {


    private HotelManager hotelManager=new HotelManager();
    private AdminManager adminManager;
    private Stage ManagerStage=new Stage();
    private boolean Owner;
    public TextField UserName;
    public TextField Pass;
    public Label LoginError;
    //
    public HelloController() throws IOException {
        try {
            adminManager=new AdminManager("AdminList");
        }
        catch (Exception e) {
            System.out.println("failed to load admin list");
        }

    }

    public void Login(ActionEvent actionEvent)  {
        Admin admin=new Admin();
        String userName = UserName.getText();
        if(userName.length()==0) {
            LoginError.setText("Please enter your UserName");
        }
        boolean LoginCheck=admin.SetName(userName);
        if(!LoginCheck) {
            LoginError.setText("Invalid UserName");
        }
        String pass = Pass.getText();
        LoginCheck=admin.SetPassword(pass);
        if(!LoginCheck) {
            LoginError.setText("Password should be between 5 and 10 characters");
        }
        Admin[] AdminArray;
        int cAdminArray;
        try {
            adminManager.Array2Admin();
            AdminArray=adminManager.GetArray();
            cAdminArray=adminManager.GetRowCount();
        }
        catch (Exception e) {
            LoginError.setText("failed to load admin list");
            return;
        }

        LoginCheck=false;
        for (int x=0;x<cAdminArray;x++) {
            if(AdminArray[x].GetName().equals(userName) && AdminArray[x].GetPassword().equals(pass)) {
                LoginCheck=true;
                Owner=AdminArray[x].GetOwner();
                System.out.println(Owner);
                break;
            }
        }

        if(LoginCheck) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ManagerPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                ManagerStage.setScene(scene);
                ManagerStage.show();
                LoginError.setText("Successfully logged in");
            }
            catch (Exception e) {
                LoginError.setText("failed to load ManagerPage");
                return;
            }
        }

    }

    public void AdminManager1(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminManager.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),800,600);
            ManagerStage.setScene(scene);
            ManagerStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public TextField AdminName;
    public TextField AdminLastName;
    public TextField AdminNationalCode;
    public TextField AdminGender;
    public TextField AdminAge;
    public TextField AdminPass;
    public TextField AdminPhoneNumber;
    public Label AddAdminError;

    public void AddAdmin(ActionEvent actionEvent) {
        Admin admin=new Admin();
        String Name=AdminName.getText();
        String LastName=AdminLastName.getText();
        String NationalCode=AdminNationalCode.getText();
        String Gender=AdminGender.getText();
        String Age=AdminAge.getText();
        String Pass=AdminPass.getText();
        String PhoneNumber=AdminPhoneNumber.getText();
        boolean Check=admin.SetName(Name);
        if(!Check) {
            AddAdminError.setText("Invalid Name");
            return;
        }
        Check=admin.SetLastName(LastName);
        if(!Check) {
            AddAdminError.setText("Invalid LastName");
            return;
        }
        Check=admin.SetNationalCode(NationalCode);
        if(!Check) {
            AddAdminError.setText("Invalid NationalCode");
            return;
        }
        Check=admin.SetGender(Gender);
        if(!Check) {
            AddAdminError.setText("Invalid Gender");
            return;
        }
        int AGE;
        try {
            AGE=Integer.parseInt(Age);
        }
        catch (Exception e) {
            AddAdminError.setText("Invalid Age");
            return;
        }
        Check=admin.SetAge(AGE);
        if(!Check) {
            AddAdminError.setText("Invalid Age");
            return;
        }
        Check=admin.SetPassword(Pass);
        if(!Check) {
            AddAdminError.setText("Invalid Password");
            return;
        }
        Check=admin.SetPhoneNumber(PhoneNumber);
        if(!Check) {
            AddAdminError.setText("Invalid Phone Number");
            return;
        }

        admin.SetID((int) (Math.random())*9999);
        try {
            adminManager.AddAdmin(admin);
        }
        catch (Exception e) {
            AddAdminError.setText("failed to add admin");
            return;
        }
    }


    public TextArea AdminSearchResult;
    public Label AdminSearchError;
    public TextField AdminSearchBox;
    private int AdminSearchID=0;

    public void AdminSearch(ActionEvent actionEvent) {
        String ID=AdminSearchBox.getText();
        int ID2=0;
        try {
            ID2=Integer.parseInt(ID);
            AdminSearchID=ID2;
        }
        catch (Exception e) {
            AdminSearchError.setText("Invalid ID");
        }
        String R=null;
        try {
            R=adminManager.Search(ID2);
        }
        catch (Exception e) {
            AdminSearchError.setText("failed to search");
        }
        if(R==null) {
            AdminSearchError.setText("Invalid ID");
            return;
        }
        AdminSearchResult.setText(R);

    }



    public Label AdminAllDataError;
    public TextArea AllAdminData;
    public void SetAllAdminData(ActionEvent actionEvent) {
        AllAdminData.clear();
        AllAdminData.setEditable(false);
        int c=0;
        Admin[] A;
        try {
            A=adminManager.GetArray();
            c=adminManager.GetRowCount();
        }
        catch (Exception e) {
            AdminAllDataError.setText("failed to load admin list");
            return;
        }
        for (int x=0;x<c;x++){
            AllAdminData.appendText(A[x].toString()+"\n");
        }


    }



    public void AdminDelete(ActionEvent actionEvent) {
        try {
            adminManager.Delete(AdminSearchID);
        }
        catch (Exception e) {
            AdminSearchError.setText("failed to delete admin");
        }
        AdminSearchError.setText("Successfully Deleted");
        AdminSearchResult.clear();
        AdminSearchBox.clear();
    }


    public void AdminUpdate(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminUpdate.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),800,600);
            ManagerStage.setScene(scene);
            ManagerStage.show();
        }
        catch (Exception e){
            AdminSearchError.setText("failed to load UpdatePage");
        }

    }


    public TextField AdminNameU;
    public TextField AdminLastNameU;
    public TextField AdminNationalCodeU;
    public TextField AdminGenderU;
    public TextField AdminAgeU;
    public TextField AdminPassU;
    public TextField AdminPhoneNumberU;
    public Label AddAdminErrorU;
    public void UpdateAdmin(ActionEvent actionEvent) {
        Admin admin=new Admin();
        String Name=AdminNameU.getText();
        String LastName=AdminLastNameU.getText();
        String NationalCode=AdminNationalCodeU.getText();
        String Gender=AdminGenderU.getText();
        String Age=AdminAgeU.getText();
        String Pass=AdminPassU.getText();
        String PhoneNumber=AdminPhoneNumberU.getText();
        boolean Check=admin.SetName(Name);
        if(!Check) {
            AddAdminErrorU.setText("Invalid Name");
            return;
        }
        Check=admin.SetLastName(LastName);
        if(!Check) {
            AddAdminErrorU.setText("Invalid LastName");
            return;
        }
        Check=admin.SetNationalCode(NationalCode);
        if(!Check) {
            AddAdminErrorU.setText("Invalid NationalCode");
            return;
        }
        Check=admin.SetGender(Gender);
        if(!Check) {
            AddAdminErrorU.setText("Invalid Gender");
            return;
        }
        int AGE;
        try {
            AGE=Integer.parseInt(Age);
        }
        catch (Exception e) {
            AddAdminErrorU.setText("Invalid Age");
            return;
        }
        Check=admin.SetAge(AGE);
        if(!Check) {
            AddAdminErrorU.setText("Invalid Age");
            return;
        }
        Check=admin.SetPassword(Pass);
        if(!Check) {
            AddAdminErrorU.setText("Invalid Password");
            return;
        }
        Check=admin.SetPhoneNumber(PhoneNumber);
        if(!Check) {
            AddAdminErrorU.setText("Invalid Phone Number");
            return;
        }

        admin.SetID((int) (Math.random())*9999);
        try {
            adminManager.AddAdmin(admin);
        }
        catch (Exception e) {
            AddAdminErrorU.setText("failed to add admin");
            return;
        }
    }

    public TextField RoomNumber;
    public TextField RoomFloor;
    public TextField RoomPrice;
    public Label AddRoomError;
    public CheckBox RoomVip;
    public void AddRoom(ActionEvent actionEvent) throws IOException {

        String roomnumber=RoomNumber.getText();
        String roomfloor=RoomFloor.getText();
        String roomprice=RoomPrice.getText();
        Rooms room=new Rooms();



        int roomNo=0;
        try {
            roomNo=Integer.parseInt(roomnumber);
        }
        catch (Exception e) {
            AddRoomError.setText("Invalid Room Number");
            return;
        }


        int roomfl=0;
        try {
            roomfl=Integer.parseInt(roomfloor);
        }
        catch (Exception e) {
            AddRoomError.setText("Invalid Room Floor");
            return;
        }


        Double roompr=0.0;
        try {
            roompr=Double.parseDouble(roomprice);
        }
        catch (Exception e) {
            AddRoomError.setText("Invalid Room Price");
            return;
        }

        boolean check=room.setNO(roomNo);
        if(!check) {
            AddRoomError.setText("Invalid Room Number");
            return;
        }

        check=room.SetFloor(roomfl);
        if(!check) {
            AddRoomError.setText("Invalid Room Floor");
            return;
        }

        check=room.SetPrice(roompr);
        if(!check) {
            AddRoomError.setText("Invalid Room Price");
            return;
        }

        if(RoomVip.isSelected()){
            check=room.SetRoomType("VIP");
            if(!check) {
                AddRoomError.setText("Invalid Room Type");
                return;
            }
        }
        else {
            check=room.SetRoomType("Normal");
            if(!check) {
                AddRoomError.setText("Invalid Room Type");
                return;
            }
        }

        room.SetIsBussy(false);

        hotelManager.addRoom(room);










    }
}