package com.example.i_s;
import Common.Commons;
import Common.Members;
import EntityManagers.MemberManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {





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
    private TextArea AllData;




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
        int t=memberManager.IndexSearchMember((int) (Math.random()*9999));
        int Rndm=0;
        while (t!=-1){
            Rndm=(int) (Math.random()*9999);
            t=memberManager.IndexSearchMember(Rndm);
        }

        Members T=new Members(MNAME,LNAME,NCODE,AGE,G,PHONENUM,Rndm);
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

    private int MemberSearchID;

    public void SearchMemeber(ActionEvent actionEvent) throws IOException {
        String Box=SearchBoxM.getText();
        if (Box.length()<1 && Box.length()>4) {
            SearchResult.setText("کد عضویت عددی است بین 0 تا 9999");
            SearchBoxM.clear();
            return;
        }
        Members T=new Members();
        for (int x=0;x<Box.length();x++) {
            if(!(Box.charAt(x)>='0' && Box.charAt(x)<='9')) {
                SearchResult.setText("کد عضویت عددی است بین 0 تا 9999");
                SearchBoxM.clear();
                return;
            }
        }
        MemberSearchID=Integer.parseInt(Box);
        String Result=memberManager.SearchMember(Integer.parseInt(Box));
        if (Result==null) {
            SearchResult.setText("وجود ندارد");
            SearchBoxM.clear();
            return;
        }

        SearchResult.setText(Result);
    }




    public void DeleteMember(ActionEvent actionEvent) throws IOException {
        if(SearchResult.equals("وجود ندارد"))
            return;
        if (SearchResult.getText().length()==0)
            return;
        String[] A=SearchResult.getText().split(Commons.Commons);
        Members T=new Members(A[0],A[1],A[2],Integer.parseInt(A[3]),A[4],A[5],Integer.parseInt(A[6]));
        boolean Check=memberManager.DeleteMember(T);
        if(Check) {
            SearchResult.setText("با موفقیت پاک شد");
            SearchBoxM.clear();
        }
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
        if(SearchResult.equals("وجود ندارد"))
            return;
        if (SearchResult.getText().length()==0)
            return;
        FXMLLoader loader=new FXMLLoader(HelloController.class.getResource("UpdateMember.fxml"));
        Scene scene=new Scene(loader.load(),800,600);
        Update.setScene(scene);
        Update.show();
    }

    public void SetUpdateMember(ActionEvent actionEvent) throws IOException {
        String MNAME=MemberNameU.getText();
        if (MNAME.length()==0) {
            MErrorU.setText("لطفا نام را وارد کنید");
            return;
        }
        String LNAME=MemberLastNameU.getText();
        if (LNAME.length()==0) {
            MErrorU.setText("لطفا نام خانوادگی را وارد کنید");
            return;
        }
        String NCODE=NCodeU.getText();
        if (NCODE.length()==0) {
            MErrorU.setText("لطفا کد ملی را وارد کنید");
            return;
        }
        String PHONENUM=PhoneNUMU.getText();
        if (PHONENUM.length()==0) {
            MErrorU.setText("لطفا شماره موبایل را وارد کنید");
            return;
        }
        int AGE;
        try {
            AGE=Integer.parseInt(AgeU.getText());
        }
        catch (Exception e) {
            MErrorU.setText("سن را درست وارد کنید");
            return;
        }


        if (!MaleU.isSelected() && !FemaleU.isSelected()) {
            MErrorU.setText("لطفا یک جنسیت را انتخاب کنید");
            return;
        }
        if(MaleU.isSelected() && FemaleU.isSelected()){
            MErrorU.setText("لطفا یک جنسیت را انتخاب کنید");
            MaleU.setSelected(false);
            FemaleU.setSelected(false);
            return;
        }
        String G=null;
        if(MaleU.isSelected() && !FemaleU.isSelected()){
            G="Male";
        }
        if(FemaleU.isSelected() && !MaleU.isSelected()){
            G="Female";
        }
        int t=memberManager.IndexSearchMember((int) (Math.random()*9999));
        int Rndm=0;
        while (t!=-1){
            Rndm=(int) (Math.random()*9999);
            t=memberManager.IndexSearchMember(Rndm);
        }

        Members T=new Members(MNAME,LNAME,NCODE,AGE,G,PHONENUM,Rndm);
        int temp = T.SetName(MNAME);
        if (temp==0) {
            MErrorU.setText("نام را درست وارد کنید");
            MemberNameU.clear();
            return;
        }
        temp=T.SetLastName(LNAME);
        if (temp==0) {
            MErrorU.setText("نام خانوادگی را درست وارد کنید");
            MemberLastNameU.clear();
            return;
        }
        temp=T.SetNationalCode(NCODE);
        if (temp==0) {
            MErrorU.setText("کد ملی را درست وارد کنید");
            NCodeU.clear();
            return;
        }
        temp=T.SetAge(AGE);
        if (temp==0) {
            MErrorU.setText("سن را درست وارد کنید");
            AgeU.clear();
            return;
        }

        temp=T.SetPhoneNumber(PHONENUM);
        if (temp==0) {
            MErrorU.setText("شماره تلفن را درست وارد کنید");
            PhoneNUMU.clear();
            return;
        }
        T.SetGender(G);


        String[] S=memberManager.SearchMember(MemberSearchID).split(Commons.Commons);
        Members T1=new Members(S[0],S[1],S[2],Integer.parseInt(S[3]),S[4],S[5],Integer.parseInt(S[6]));
        MErrorU.setText("ثبت شد!");
        memberManager.UpdateMember(T1,T);
        MemberNameU.clear();
        MemberLastNameU.clear();
        NCodeU.clear();
        PhoneNUMU.clear();
        AgeU.clear();
        MaleU.setSelected(false);
        FemaleU.setSelected(false);

    }


    public void SetDataMem(ActionEvent actionEvent) {
        Members M[]=memberManager.GetArray();
        int cM=memberManager.GetLengthArray();
        AllData.setEditable(false);
        for (int x=0;x<cM;x++) {
            AllData.appendText(M[x].toString()+"\n");
        }
    }
}