package com.example.i_s;
import Common.Commons;
import Common.Drinks;
import Common.Foods;
import Common.Members;
import EntityManagers.DrinksManager;
import EntityManagers.FoodsManager;
import EntityManagers.MemberManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.stream.events.EntityDeclaration;
import java.io.IOException;
public class HelloController {


    public HelloController() throws IOException {
    }


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


    //------------------------------------------------------------------------------------------------------------------------->>>Food Section
    private Stage FoodStage=new Stage();
    private FoodsManager FOODS=new FoodsManager("FoodList");

    @FXML
    private TextField FoodName;
    @FXML
    private TextField TypeFood;
    @FXML
    private TextField EntityFood;
    @FXML
    private TextField FoodPrice;
    @FXML
    private Label MErrorFood;
    @FXML
    private TextField SearchBoxF;
    @FXML
    private Label SearchResultFood;
    @FXML
    private TextArea AllDataFood;

    private int SearchFoodID;


    public void FoodsCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("Foods.fxml"));
        Scene scene=new Scene(loader.load(),800,600);
        FoodStage.setScene(scene);
        FoodStage.show();
    }

    public void SetFood(ActionEvent actionEvent) throws IOException {
        String Name=FoodName.getText();
        if (Name.length()==0) {
            MErrorFood.setText("لطفا نام غذا را وارد کنید");
            return;
        }
        String Type=TypeFood.getText();
        if (Type.length()==0) {
            MErrorFood.setText("لطفا نوع غذا را وارد کنید");
            return;
        }
        String Entity=EntityFood.getText();
        if (Entity.length()==0) {
            MErrorFood.setText("لطفا تعداد غذا را وارد کنید");
            return;
        }
        String Price=FoodPrice.getText();
        if (Price.length()==0) {
            MErrorFood.setText("لطفا قیمت غذا را وارد کنید");
            return;
        }
        Foods F=new Foods();
        boolean Check=F.SetFoodName(Name);
        if (!Check) {
            MErrorFood.setText("نامعتبر!");
            FoodName.clear();
            return;
        }
        Check=F.SetFoodType(Type);
        if (!Check) {
            MErrorFood.setText("نامعتبر!");
            TypeFood.clear();
            return;
        }
        double Price2;
        try {
            Price2=Double.parseDouble(Price);
        }
        catch (Exception e) {
            MError.setText("نامعتبر!");
            FoodPrice.clear();
            return;
        }
        Check=F.SetFoodPrice(Price2);
        if (!Check) {
            MErrorFood.setText("نامعتبر!");
            FoodPrice.clear();
            return;
        }
        Check=F.SetFoodQuantity(Integer.parseInt(Entity));
        if (!Check) {
            MErrorFood.setText("نامعتبر!");
            EntityFood.clear();
            return;
        }
        F.SetID((int) (Math.random()*99));
        FOODS.AddFood(F);
        FoodName.clear();
        TypeFood.clear();
        EntityFood.clear();
        FoodPrice.clear();
    }

    public void SearchFood(ActionEvent actionEvent) throws IOException {
        if (!(SearchBoxF.getText().length()>0 && SearchBoxF.getText().length()<3)){
            SearchResultFood.setText("نامعتبر!");
            SearchBoxF.clear();
            return;
        }
        Foods F=new Foods();
        boolean Check=F.SetID(Integer.parseInt(SearchBoxF.getText()));
        if (!Check) {
            SearchResultFood.setText("نامعتبر!");
            SearchBoxF.clear();
            return;
        }
        String TE=FOODS.SearchFood(Integer.parseInt(SearchBoxF.getText()));
        SearchResultFood.setText(TE);
        SearchBoxF.clear();

    }

    public void UpdateStageFood(ActionEvent actionEvent) throws IOException {
        if(SearchResultFood.getText().length()==0){
            return;
        }
        if (SearchResultFood.getText().equals("نامعتبر!")) {
            return;
        }
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("UpdateFood.fxml"));
        Scene scene=new Scene(loader.load(),800,600);
        FoodStage.setScene(scene);
        FoodStage.show();
    }

    public void DeleteFood(ActionEvent actionEvent) throws IOException {
        String S1=SearchResultFood.getText();
        String[] F1=S1.split(Commons.Commons);
        Foods F=new Foods(Integer.parseInt(F1[0]),F1[1],F1[2],Double.parseDouble(F1[3]),Integer.parseInt(F1[4]));

        FOODS.DeleteFood(F);
        SearchResultFood.setText("");
        SearchBoxF.clear();
    }

    public void SetDataFood(ActionEvent actionEvent) {
        Foods M[]=FOODS.GetArray();
        int cM=FOODS.GetLengthArray();
        AllDataFood.setEditable(false);
        for (int x=0;x<cM;x++) {
            AllDataFood.appendText(M[x].toString()+"\n");
        }
    }



    @FXML
    private TextField FoodNameU;
    @FXML
    private TextField TypeFoodU;
    @FXML
    private TextField EntityFoodU;
    @FXML
    private TextField FoodPriceU;
    @FXML
    private Label MErrorFoodU;

    public void SetUpdateFood(ActionEvent actionEvent) throws IOException {
        String Name=FoodNameU.getText();
        if (Name.length()==0) {
            MErrorFoodU.setText("لطفا نام غذا را وارد کنید");
            return;
        }
        String Type=TypeFoodU.getText();
        if (Type.length()==0) {
            MErrorFoodU.setText("لطفا نوع غذا را وارد کنید");
            return;
        }
        String Entity=EntityFoodU.getText();
        if (Entity.length()==0) {
            MErrorFoodU.setText("لطفا تعداد غذا را وارد کنید");
            return;
        }
        String Price=FoodPriceU.getText();
        if (Price.length()==0) {
            MErrorFoodU.setText("لطفا قیمت غذا را وارد کنید");
            return;
        }
        Foods F=new Foods();
        boolean Check=F.SetFoodName(Name);
        if (!Check) {
            MErrorFoodU.setText("نامعتبر!");
            FoodNameU.clear();
            return;
        }
        Check=F.SetFoodType(Type);
        if (!Check) {
            MErrorFoodU.setText("نامعتبر!");
            TypeFoodU.clear();
            return;
        }
        double Price2;
        try {
            Price2=Double.parseDouble(Price);
        }
        catch (Exception e) {
            MErrorU.setText("نامعتبر!");
            FoodPriceU.clear();
            return;
        }
        Check=F.SetFoodPrice(Price2);
        if (!Check) {
            MErrorFoodU.setText("نامعتبر!");
            FoodPriceU.clear();
            return;
        }
        Check=F.SetFoodQuantity(Integer.parseInt(Entity));
        if (!Check) {
            MErrorFoodU.setText("نامعتبر!");
            EntityFoodU.clear();
            return;
        }
        F.SetID((int) (Math.random()*99));
        String Search=FOODS.SearchFood(SearchFoodID);
        String[] T1=Search.split(Commons.Commons);
        Foods F1=new Foods(Integer.parseInt(T1[0]),T1[1],T1[2],Double.parseDouble(T1[3]),Integer.parseInt(T1[4]) );


        FOODS.UpdateFood(F1,F);
        MErrorFoodU.setText("ثبت شد!");
        EntityFoodU.clear();
        FoodPriceU.clear();
        FoodNameU.clear();
        TypeFoodU.clear();
    }



    //------------------------------------------------------------------------------------------------------------------------>>>Drinks

    Stage DrinkStage=new Stage();

    DrinksManager drinksManager=new DrinksManager("DrinksList");

    public void DrinksCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("Drinks.fxml"));
        Scene scene=new Scene(loader.load(),800,600);
        DrinkStage.setScene(scene);
        DrinkStage.show();
    }



    @FXML
    private TextField DrinkName;
    @FXML
    private TextField DrinkType;
    @FXML
    private TextField DrinkPrice;
    @FXML
    private TextField DrinkEntity;
    @FXML
    private Label MErrorDrink;
    @FXML
    private TextField SearchBoxD;
    @FXML
    private Label SearchResultDrink;
    @FXML
    private TextArea AllDataDrink;

    public void SetDrink(ActionEvent actionEvent) throws IOException {
        String Name=DrinkName.getText();
        if (Name.length()==0) {
            MErrorDrink.setText("لطفا نام نوشیدنی را وارد کنید");
            return;
        }
        String Type=DrinkType.getText();
        if (Type.length()==0) {
            MErrorDrink.setText("لطفا نوع نوشیدنی را وارد کنید");
            return;
        }
        String Entity=DrinkEntity.getText();
        if (Entity.length()==0) {
            MErrorDrink.setText("لطفا تعداد نوشیدنی را وارد کنید");
            return;
        }
        String Price=DrinkPrice.getText();
        if (Price.length()==0) {
            MErrorDrink.setText("لطفا قیمت نوشیدنی را وارد کنید");
            return;
        }
        Drinks F=new Drinks();
        boolean Check=F.SetDrinkName(Name);
        if (!Check) {
            MErrorDrink.setText("نامعتبر!");
            DrinkName.clear();
            return;
        }
        Check=F.SetDrinkType(Type);
        if (!Check) {
            MErrorDrink.setText("نامعتبر!");
            DrinkType.clear();
            return;
        }
        double Price2;
        try {
            Price2=Double.parseDouble(Price);
        }
        catch (Exception e) {
            MErrorDrink.setText("نامعتبر!");
            DrinkPrice.clear();
            return;
        }
        Check=F.SetDrinkPrice(Price2);
        if (!Check) {
            MErrorDrink.setText("نامعتبر!");
            DrinkPrice.clear();
            return;
        }
        Check=F.SetDrinkQuantity(Integer.parseInt(Entity));
        if (!Check) {
            MErrorDrink.setText("نامعتبر!");
            DrinkEntity.clear();
            return;
        }
        F.SetID((int) (Math.random()*99));
        drinksManager.AddDrink(F);
        MErrorDrink.setText("ثبت شد!");
        DrinkName.clear();
        DrinkType.clear();
        DrinkEntity.clear();
        DrinkPrice.clear();
    }

    public void SearchDrink(ActionEvent actionEvent) throws IOException {
        if (!(SearchBoxD.getText().length()>0 && SearchBoxD.getText().length()<3)){
            SearchResultDrink.setText("نامعتبر!");
            SearchBoxD.clear();
            return;
        }
        Drinks F=new Drinks();
        boolean Check=F.SetID(Integer.parseInt(SearchBoxD.getText()));
        if (!Check) {
            SearchResultDrink.setText("نامعتبر!");
            SearchBoxD.clear();
            return;
        }
        String TE= drinksManager.SearchDrink(Integer.parseInt(SearchBoxD.getText()));
        SearchResultDrink.setText(TE);
        SearchBoxD.clear();
    }

    public void UpdateStageDrink(ActionEvent actionEvent) throws IOException {
        if(SearchResultDrink.getText().length()==0){
            return;
        }
        if (SearchResultDrink.getText().equals("نامعتبر!")) {
            return;
        }
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("UpdateDrink.fxml"));
        Scene scene=new Scene(loader.load(),800,600);
        DrinkStage.setScene(scene);
        DrinkStage.show();
    }
    
    


    public void DeleteDrink(ActionEvent actionEvent) throws IOException {
        String S1=SearchResultDrink.getText();
        String[] F1=S1.split(Commons.Commons);
        Drinks F=new Drinks(Integer.parseInt(F1[0]),F1[1],F1[2],Double.parseDouble(F1[3]),Integer.parseInt(F1[4]));

        drinksManager.DeleteDrink(F);
        SearchResultDrink.setText("");
        SearchBoxD.clear();
    }

    public void SetDataDrink(ActionEvent actionEvent) {
        Drinks D[]=drinksManager.GetArray();
        int cD=drinksManager.GetLengthArray();
        AllDataDrink.setEditable(false);
        for (int x=0;x<cD;x++) {
            AllDataDrink.appendText(D[x].toString()+"\n");
        }
    }



    @FXML
    private TextField DrinkNameU;
    @FXML
    private TextField DrinkTypeU;
    @FXML
    private TextField DrinkPriceU;
    @FXML
    private TextField DrinkEntityU;
    @FXML
    private Label MErrorDrinkU;




    public void SetUpdateDrink(ActionEvent actionEvent) throws IOException {
        String Name=DrinkNameU.getText();
        if (Name.length()==0) {
            MErrorDrinkU.setText("لطفا نام غذا را وارد کنید");
            return;
        }
        String Type=DrinkTypeU.getText();
        if (Type.length()==0) {
            MErrorFoodU.setText("لطفا نوع غذا را وارد کنید");
            return;
        }
        String Entity=DrinkEntityU.getText();
        if (Entity.length()==0) {
            MErrorDrinkU.setText("لطفا تعداد غذا را وارد کنید");
            return;
        }
        String Price=DrinkPriceU.getText();
        if (Price.length()==0) {
            MErrorDrinkU.setText("لطفا قیمت غذا را وارد کنید");
            return;
        }
        Drinks F=new Drinks();
        boolean Check=F.SetDrinkName(Name);
        if (!Check) {
            MErrorDrinkU.setText("نامعتبر!");
            DrinkNameU.clear();
            return;
        }
        Check=F.SetDrinkType(Type);
        if (!Check) {
            MErrorDrinkU.setText("نامعتبر!");
            DrinkTypeU.clear();
            return;
        }
        double Price2;
        try {
            Price2=Double.parseDouble(Price);
        }
        catch (Exception e) {
            MErrorDrinkU.setText("نامعتبر!");
            DrinkPriceU.clear();
            return;
        }
        Check=F.SetDrinkPrice(Price2);
        if (!Check) {
            MErrorDrinkU.setText("نامعتبر!");
            DrinkPriceU.clear();
            return;
        }
        Check=F.SetDrinkQuantity(Integer.parseInt(Entity));
        if (!Check) {
            MErrorDrinkU.setText("نامعتبر!");
            DrinkEntityU.clear();
            return;
        }
        F.SetID((int) (Math.random()*99));
        String Search= drinksManager.SearchDrink(SearchFoodID);
        String[] T1=Search.split(Commons.Commons);
        Drinks F1=new Drinks(Integer.parseInt(T1[0]),T1[1],T1[2],Double.parseDouble(T1[3]),Integer.parseInt(T1[4]) );


        drinksManager.UpdateDrink(F1,F);
        MErrorDrinkU.setText("ثبت شد!");
        DrinkEntityU.clear();
        DrinkPriceU.clear();
        DrinkNameU.clear();
        DrinkTypeU.clear();
    }
}