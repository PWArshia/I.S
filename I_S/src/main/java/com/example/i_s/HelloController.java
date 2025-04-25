package com.example.i_s;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloController {





    // ---------------------------------->>>Room Section

    Stage RoomWindow=new Stage();


    @FXML
    private TextField RoomNum;
    @FXML
    private TextField tbNum;
    @FXML
    private TextField PriceV;
    @FXML
    private CheckBox Type;


    @FXML
    public void RoomsCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader RoomsLoader = new FXMLLoader(getClass().getResource("Rooms.fxml"));
        Scene RoomsScene = new Scene(RoomsLoader.load(),800,600);
        RoomWindow.setScene(RoomsScene);
        RoomWindow.setTitle("Rooms");
        RoomWindow.show();


    }

    public void SearchRoom(ActionEvent actionEvent) {

    }

    public void SetRoom(ActionEvent actionEvent) {
        String RMNUM=RoomNum.getText();
        String TBNUM=tbNum.getText();
        String PRICEV=PriceV.getText();
        String TYPE;
        if(Type.isSelected()){
            TYPE="VIP";
        }
        else{
            TYPE="normal";
        }
    }


    //--------------------------------------->>>Member Section

    Stage MemberWindow=new Stage();


    @FXML
    private TextField MemberName;
    @FXML
    private TextField MemberLastName;
    @FXML
    private TextField NCode;
    @FXML
    private TextField PhoneNUM;
    @FXML
    private TextField Age;
    @FXML
    private CheckBox Male;
    @FXML
    private CheckBox Female;


    @FXML
    public void MembersCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader MembersLoader = new FXMLLoader(getClass().getResource("Members.fxml"));
        Scene MembersScene = new Scene(MembersLoader.load(),800,600);
        MemberWindow.setScene(MembersScene);
        MemberWindow.setTitle("Members");
        MemberWindow.show();
    }




    public void SetMember(ActionEvent actionEvent) {
        String MNAME=MemberName.getText();
        String LNAME=MemberLastName.getText();
        String NCODE=NCode.getText();
        String PHONENUM=PhoneNUM.getText();
        String AGE=Age.getText();
        if(Male.isSelected()){

        }
        if(Female.isSelected()){

        }
    }

    public void SearchMemeber(ActionEvent actionEvent) {
    }
}