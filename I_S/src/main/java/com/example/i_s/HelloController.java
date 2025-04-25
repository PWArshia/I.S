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
    Stage MemberWindow=new Stage();
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
    public void MembersCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader MembersLoader = new FXMLLoader(getClass().getResource("Members.fxml"));
        Scene MembersScene = new Scene(MembersLoader.load(),800,600);
        MemberWindow.setScene(MembersScene);
        MemberWindow.setTitle("Members");
        MemberWindow.show();
    }
    @FXML
    public void RoomsCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader RoomsLoader = new FXMLLoader(getClass().getResource("Rooms.fxml"));
        Scene RoomsScene = new Scene(RoomsLoader.load(),800,600);
        RoomWindow.setScene(RoomsScene);
        RoomWindow.setTitle("Rooms");
        RoomWindow.show();
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

    public void SearchRoom(ActionEvent actionEvent) {

    }

    public void SetRoom(ActionEvent actionEvent) {
    }

    public void SetMember(ActionEvent actionEvent) {
    }

    public void SearchMemeber(ActionEvent actionEvent) {
    }
}