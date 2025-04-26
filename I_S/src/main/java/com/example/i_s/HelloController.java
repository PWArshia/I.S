package com.example.i_s;
import Common.Commons;
import Common.Members;
import EntityManagers.MemberManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController{





    // ------------------------------------------------------------------------------------------------>>>Room Section

    Stage RoomWindow=new Stage();


    @FXML
    private TextField RoomNum;
    @FXML
    private TextField tbNum;
    @FXML
    private TextField PriceV;
    @FXML
    private CheckBox Type;

    public HelloController() throws IOException {
    }


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


    //------------------------------------------------------------------------------------------------------------------------------>>>Member Section
    Stage MemberWindow=new Stage();
    MemberManager memberManager=new MemberManager("MembersList");


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
    private Label MError;
    @FXML
    private Label SearchResult;
    @FXML
    private TextField SearchBoxM;
    @FXML
    private Button BTNDeleteMember;
    @FXML
    private Button BTNUpdateMember;


    @FXML
    public void MembersCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader MembersLoader = new FXMLLoader(getClass().getResource("Members.fxml"));
        Scene MembersScene = new Scene(MembersLoader.load(),800,600);
        MemberWindow.setScene(MembersScene);
        MemberWindow.setTitle("Members");
        MemberWindow.show();
    }




    public void SetMember(ActionEvent actionEvent) throws IOException {

        String MNAME=MemberName.getText();
        if (MNAME.length()==0) {
            MError.setText("لطفا نام را وارد کنید");
            return;
        }
        String LNAME=MemberLastName.getText();
        if (LNAME.length()==0) {
            MError.setText("لطفا نام خانوادگی را وارد کنید");
            return;
        }
        String NCODE=NCode.getText();
        if (NCODE.length()==0) {
            MError.setText("لطفا کد ملی را وارد کنید");
            return;
        }
        String PHONENUM=PhoneNUM.getText();
        if (PHONENUM.length()==0) {
            MError.setText("لطفا شماره موبایل را وارد کنید");
            return;
        }
        int AGE;
        try {
            AGE=Integer.parseInt(Age.getText());
        }
        catch (Exception e) {
            MError.setText("سن را درست وارد کنید");
            return;
        }


        if (!Male.isSelected() && !Female.isSelected()) {
            MError.setText("لطفا یک جنسیت را انتخاب کنید");
            return;
        }
        if(Male.isSelected() && Female.isSelected()){
            MError.setText("لطفا یک جنسیت را انتخاب کنید");
            Male.setSelected(false);
            Female.setSelected(false);
            return;
        }
        String G=null;
        if(Male.isSelected() && !Female.isSelected()){
            G="Male";
        }
        if(Female.isSelected() && !Male.isSelected()){
            G="Female";
        }
        Members T=new Members(MNAME,LNAME,NCODE,AGE,G,PHONENUM);
        int temp = T.SetName(MNAME);
        if (temp==0) {
            MError.setText("نام را درست وارد کنید");
            MemberName.clear();
            return;
        }
        temp=T.SetLastName(LNAME);
        if (temp==0) {
            MError.setText("نام خانوادگی را درست وارد کنید");
            MemberLastName.clear();
            return;
        }
        temp=T.SetNationalCode(NCODE);
        if (temp==0) {
            MError.setText("کد ملی را درست وارد کنید");
            NCode.clear();
            return;
        }
        temp=T.SetAge(AGE);
        if (temp==0) {
            MError.setText("سن را درست وارد کنید");
            Age.clear();
            return;
        }

        temp=T.SetPhoneNumber(PHONENUM);
        if (temp==0) {
            MError.setText("شماره تلفن را درست وارد کنید");
            PhoneNUM.clear();
            return;
        }
        T.SetGender(G);


        memberManager.AddMember(T);
        MError.setText("ثبت شد!");
        MemberName.clear();
        MemberLastName.clear();
        NCode.clear();
        PhoneNUM.clear();
        Age.clear();
        Male.setSelected(false);
        Female.setSelected(false);
    }

    public void SearchMemeber(ActionEvent actionEvent) throws IOException {
        Members A=new Members();
        int t=A.SetNationalCode(SearchBoxM.getText());
        if(t==0){
            SearchResult.setText("کد ملی را درست وارد کنید");
            SearchBoxM.clear();
            return;
        }
        String Result=memberManager.SearchMember(SearchBoxM.getText());

        if (Result==null) {
            SearchResult.setText("پیدا نشد!");
            SearchBoxM.clear();
            return;
        }
        SearchResult.setText(Result);


    }



    public void DeleteMember(ActionEvent actionEvent) throws IOException {
        String[] T=SearchResult.getText().split(Commons.Commons);

        memberManager.DeleteMember(SearchBoxM.getText());
        SearchResult.setText("");
    }


    Stage Update=new Stage();
    @FXML
    private TextField MemberNameU;
    @FXML
    private TextField MemberLastNameU;
    @FXML
    private TextField NCodeU;
    @FXML
    private TextField PhoneNUMU;
    @FXML
    private TextField AgeU;
    @FXML
    private CheckBox MaleU;
    @FXML
    private CheckBox FemaleU;
    @FXML
    private Label MErrorU;


    public void UpdateStageMember(ActionEvent actionEvent) throws IOException {
        FXMLLoader MembersULoader = new FXMLLoader(getClass().getResource("UpdateMember.fxml"));
        Scene scene=new Scene(MembersULoader.load(),800,600);
        Update.setScene(scene);
        Update.setTitle("UpdateMember");
        Update.show();

        String R[]=SearchResult.getText().split(Commons.Commons);
        System.out.println(R[0]);
        MemberNameU.setText(R[0]);
        MemberLastNameU.setText(R[1]);
        NCodeU.setText(R[2]);
        AgeU.setText(R[3]);
        if (R[4].equals("male")) {
            MaleU.setSelected(true);
        }
        if (R[4].equals("female")) {
            FemaleU.setSelected(true);
        }
        PhoneNUMU.setText(R[5]);
    }

    public void SetUpdateMember(ActionEvent actionEvent) {
    }
}