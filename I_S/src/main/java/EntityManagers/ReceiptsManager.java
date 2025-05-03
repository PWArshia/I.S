package EntityManagers;

import Common.*;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReceiptsManager {
    private MemberManager memberManager;
    private FoodsManager foodsManager;
    private DrinksManager drinksManager;
    private DessertManager dessertManager;
    private TextFile_Manager textFileManager;
    private Receipt[] receipts=new Receipt[1000];


    public ReceiptsManager(String fileName,MemberManager memberManager1, FoodsManager foodsManager1,
                           DrinksManager drinksManager1,DessertManager dessertManager1) {
        this.memberManager = memberManager1;
        this.foodsManager = foodsManager1;
        this.drinksManager = drinksManager1;
        this.dessertManager = dessertManager1;
        this.textFileManager = new TextFile_Manager(fileName);
    }


    public void Array2TextFile() throws IOException {
        int cT=textFileManager.getRowCount();
        String T[]=textFileManager.getArray();
        for (int i = 0; i < cT; i++) {
            textFileManager.AppendRow(receipts[i].toString());
        }
    }


    public void TextFile2Array() throws IOException {
        String T[]=this.textFileManager.getArray();
        int cT=this.textFileManager.getRowCount();
        for (int i = 0; i < cT; i++) {
            String R[] = T[i].split(Commons.Commons);
            //--------------------------------------->>>ID
            int IDCode=Integer.parseInt(R[0]);

            //--------------------------------------->>>Date

            String date = R[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);


            //--------------------------------------->>>Member
            Members M1 = new Members(R[3], R[4], R[5], Integer.parseInt(R[6]), R[7], R[8], Integer.parseInt(R[9]));
            //--------------------------------------->>>Foods
            Foods[] F=new Foods[100];
            int c=0;
            int x=11;
            while (!R[x].equals("Dessert")){
                F[c++]=new Foods(Integer.parseInt(R[x]),R[x+1],R[x+2],Double.parseDouble(R[x+3])
                        ,Integer.parseInt(R[x+4]),Double.parseDouble(R[x+5]));
                x+=6;
            }
            x++;
            int FoodsCount[]=new int[100];
            for (int b=0;b<c;b++){
                FoodsCount[b]=F[b].GetFoodQuantity();
            }
            int FoodsCodes[]=new int[100];
            for (int b=0;b<c;b++){
                FoodsCodes[b]=F[b].GetID();
            }
            //--------------------------------------->>>Dessert
            Dessert[] D1=new Dessert[100];
            int c1=0;
            while (!R[x].equals("Drinks")){
                D1[c1++]=new Dessert(Integer.parseInt(R[x]),R[x+1],Double.parseDouble(R[x+2]),
                        Integer.parseInt(R[x+4]),Double.parseDouble((R[x+3])));
                x+=5;
            }
            x++;
            int DessertCount[]=new int[100];
            for (int b=0;b<c1;b++){
                DessertCount[b]=D1[b].GetDessertQuantity();
            }
            int DessertCodes[]=new int[100];
            for (int b=0;b<c1;b++){
                DessertCodes[b]=D1[b].GetId();
            }
            //------------------------------------------>>>Drink
            Drinks[] D2=new Drinks[100];
            int c2=0;
            while (x!=R.length-2){
                D2[c2++]=new Drinks(Integer.parseInt(R[x]),R[x+1],R[x+2],
                        Double.parseDouble(R[x+3]),Integer.parseInt(R[x+4]),Double.parseDouble(R[x+5]));
                x+=6;
            }
            int DrinkCount[]=new int[100];
            for (int b=0;b<c2;b++){
                DrinkCount[b]=D2[b].GetDrinkQuantity();
            }
            int DrinkCodes[]=new int[100];
            for (int b=0;b<c2;b++){
                DrinkCodes[b]=D2[b].GetID();
            }




            receipts[i]=new Receipt(M1.GetID(), memberManager,DessertCodes
                    ,dessertManager,DessertCount,DrinkCodes,drinksManager,
                    DrinkCount,FoodsCodes,foodsManager,FoodsCount,IDCode);

        }

        for (int i=cT;i<this.receipts.length;i++){
            this.receipts[i]=null;
        }

    }

    public void AddReceipt(Receipt receipt) throws IOException {
        this.textFileManager.AppendRow(receipt.toString());
        this.TextFile2Array();
    }

    public void UpdateReceipt(int ID,String R) throws IOException {
        int a= IndexSearchReceipts(ID);
        this.textFileManager.UpdateRow(a, R);
        this.TextFile2Array();
    }

    public void DeleteReceipt(int ID) throws IOException {
        int a= IndexSearchReceipts(ID);
        if (a==-1)
            return;
        this.textFileManager.delete_Rows(a);
        this.TextFile2Array();
    }


    public String SearchReceipts(int ID) throws IOException {
        TextFile2Array();
        int cRe=0;
        for (int x= receipts.length-1;x>=0;x--){
            if (receipts[x]!=null){
                cRe=x+1;
                break;
            }
        }

        for (int i = 0; i < cRe; i++) {
            if (this.receipts[i].GetID()==ID) {
                return receipts[i].toString();
            }
        }
        return null;
    }



    public int IndexSearchReceipts(int ID) throws IOException {
        TextFile2Array();
        int cRe=0;
        for (int x= receipts.length-1;x>=0;x--){
            if (receipts[x]!=null){
                cRe=x+1;
                break;
            }
        }

        for (int i = 0; i < cRe; i++) {
            if (this.receipts[i].GetID()==ID) {
                return i;
            }
        }
        return -1;
    }


    public Receipt[] GetReceiptsArray() throws IOException {
        TextFile2Array();
        return this.receipts;
    }

}//class
