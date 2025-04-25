package com.example.i_s;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloController {
    Stage MemberWindow=new Stage();
    Stage RoomWindow=new Stage();
    @FXML
    public void MembersCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader MembersLoader = new FXMLLoader(getClass().getResource("Members.fxml"));
        Scene MembersScene = new Scene(MembersLoader.load(),600,400);
        MemberWindow.setScene(MembersScene);
        MemberWindow.setTitle("Members");
        MemberWindow.show();
    }
    @FXML
    public void RoomsCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader RoomsLoader = new FXMLLoader(getClass().getResource("Rooms.fxml"));
        Scene RoomsScene = new Scene(RoomsLoader.load(),600,400);
        RoomWindow.setScene(RoomsScene);
        RoomWindow.setTitle("Rooms");
        RoomWindow.show();
    }
}