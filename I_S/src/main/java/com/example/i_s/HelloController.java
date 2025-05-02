package com.example.i_s;

import Common.Admin;
import EntityManagers.AdminManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloController {

    private AdminManager adminManager;
    private Stage ManagerStage=new Stage();



    public TextField UserName;
    public TextField Pass;
    public Label LoginError;

    public HelloController()  {
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
        int flag2=admin.SetName(userName);
        if(flag2==0) {
            LoginError.setText("Invalid UserName");
        }
        String pass = Pass.getText();
        boolean flag=admin.SetPassword(pass);
        if(flag) {
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

        boolean LoginCheck=false;
        for (int x=0;x<cAdminArray;x++) {
            if(AdminArray[x].GetName().equals(userName) && AdminArray[x].GetPassword().equals(pass)) {
                LoginCheck=true;
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
        }

    }
}