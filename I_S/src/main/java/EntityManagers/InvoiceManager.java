package EntityManagers;

import Common.*;
import TxtFileManager.TextFile_Manager;

import java.io.IOException;
import java.time.LocalDate;

public class InvoiceManager {
    private TextFile_Manager textFile_Manager;
    private String fileName;
    private Receipt[] receipt;
    private Dessert[] dessert=new Dessert[1000];
    private int cDessert;
    private Drinks[] drinks=new Drinks[1000];
    private int cDrink;
    private Foods[] foods=new Foods[1000];
    private int cFood;
    private LocalDate Date;
    public InvoiceManager(String fileName, Receipt[] receipts) {
        textFile_Manager=new TextFile_Manager(fileName);
        this.fileName = fileName;
        this.receipt=receipts;
    }

    private void AnalysisReceipt() {
        Date=receipt[0].GetDate();
        int cReceipt=0;
        for (int i=receipt.length-1;i>=0;i--) {
            if(receipt[i]!=null) {
                cReceipt=i+1;
                break;
            }
        }

        for (int i = 0; i < cReceipt; i++) {
            if(receipt[i]==null)
                continue;
            Receipt r=receipt[i];
            Foods[] f=r.GetFoodsArray();
            int cf=0;
            for (int j = f.length-1; j >= 0 ; j--) {
                if(f[j]!=null){
                    cf=j+1;
                    break;
                }
            }

            for (int x=0;x<cf;x++){
                int search=IndexSearchFoodID(foods,f[x].GetID());
                if(search==-1){
                    foods[cFood++]=f[x];
                }
                else{
                    foods[search].SetFoodQuantity(foods[search].GetFoodQuantity()+f[x].GetFoodQuantity());
                }
            }



            Drinks[] d2=r.GetDrinksArray();
            int cd2=0;
            for (int j = d2.length-1; j >= 0 ; j--) {
                if(d2[j]!=null){
                    cd2=j+1;
                    break;
                }
            }

            for (int x=0;x<cd2;x++){
                int search=IndexSearchDrinkID(drinks,d2[x].GetID());
                if(search==-1){
                    drinks[cDrink++]=d2[x];
                }
                else{
                    drinks[search].SetDrinkQuantity(drinks[search].GetDrinkQuantity()+d2[x].GetDrinkQuantity());
                }
            }



            Dessert[] d1=r.GetDessertsArray();
            int cd1=0;
            for (int j = d1.length-1; j >= 0 ; j--) {
                if(d1[j]!=null){
                    cd1=j+1;
                    break;
                }
            }
            for (int x=0;x<cd1;x++){
                int search=IndexSearchDessertID(dessert,d1[x].GetId());
                if(search==-1){
                    dessert[cDessert++]=d1[x];
                }
                else{
                    dessert[search].SetDessertQuantity(dessert[search].GetDessertQuantity()+d1[x].GetDessertQuantity());
                }
            }



        }
    }


    public void SetReceipt() throws IOException {
        AnalysisReceipt();
        textFile_Manager.ClearTextFile();
        textFile_Manager.AppendRow("Date:"+Date+"\n");
        textFile_Manager.AppendRow("Foods:"+"\n");
        double Total1=0.0;
        for (int x=0;x<cFood;x++){
            Total1=foods[x].GetFoodQuantity()*(foods[x].GetFoodPrice()-foods[x].GetBuyPrice());
            textFile_Manager.AppendRow(foods[x].toString()+Commons.Commons+"total:"+Total1+"\n");
        }
        textFile_Manager.AppendRow("Drinks:"+"\n");
        double Total2=0.0;
        for (int x=0;x<cDrink;x++){
            Total2=drinks[x].GetDrinkQuantity()*(drinks[x].GetDrinkPrice()-drinks[x].GetBuyPrice());
            textFile_Manager.AppendRow(drinks[x].toString()+Commons.Commons+"total:"+Total2+"\n");
        }
        textFile_Manager.AppendRow("Dessert:"+"\n");
        double Total3=0.0;
        for (int x=0;x<cDessert;x++){
            Total3=drinks[x].GetDrinkQuantity()*(drinks[x].GetDrinkPrice()-drinks[x].GetBuyPrice());
            textFile_Manager.AppendRow(dessert[x].toString()+Commons.Commons+"total:"+Total3+"\n");
        }
        double AllTotal=Total1+Total2+Total3;
        textFile_Manager.AppendRow("AllTotal:" + Commons.Commons+AllTotal+"\n");
    }



    private static int IndexSearchFoodID(Foods[] F,int ID){
        for (int i = 0; i < F.length-1; i++) {
            if(F[i]==null)
                continue;
            if(F[i].GetID()==ID)
                return i;
        }
        return -1;
    }


    private static int IndexSearchDessertID(Dessert[] d1,int ID){
        for (int i = 0; i < d1.length; i++) {
            if(d1[i]==null)
                continue;
            if(d1[i].GetId()==ID)
                return i;
        }
        return -1;
    }


    private static int IndexSearchDrinkID(Drinks[] d1,int ID){
        for (int i = 0; i < d1.length; i++) {
            if (d1[i]==null)
                continue;
            if(d1[i].GetID()==ID)
                return i;
        }
        return -1;
    }

    public void ClearInvoices() throws IOException {
        textFile_Manager.ClearTextFile();
        for (int x=0;x< receipt.length;x++){
            receipt[x]=null;
        }

        for (int x=0;x<dessert.length;x++){
            dessert[x]=null;
        }
        for (int x=0;x<drinks.length;x++){
            drinks[x]=null;
        }
        for (int x=0;x<foods.length;x++){
            foods[x]=null;
        }
        cDessert=0;
        cDrink=0;
        cFood=0;
        Date=null;
    }


}