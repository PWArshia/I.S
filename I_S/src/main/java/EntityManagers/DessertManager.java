package EntityManagers;

import Common.Commons;
import Common.Dessert;
import Common.Rooms;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DessertManager {

    private TextFile_Manager fm;
    private Dessert dessert[];
    private int searchid;


    public DessertManager(String filename) throws IOException {
        fm = new TextFile_Manager(filename);
        fm.CreateTextFile();
        this.Array2Dessert();
    }

    public void AddDessert(Dessert dessert1) throws IOException {
        fm.AppendRow(dessert1.toString());
        this.Array2Dessert();
    }

    public void Array2Dessert() throws FileNotFoundException {

        dessert=new Dessert[fm.getRowCount()+100];
        String A[]=fm.getArray();

        for (int i=0;i<fm.getRowCount();i++){
            dessert[i]=new Dessert();
            String B[]=A[i].split(Commons.Commons);

            int ID=Integer.parseInt(B[0]);
            String Name=B[1];
            double price=Double.parseDouble(B[2]);
            int No=Integer.parseInt(B[4]);
            double BuyPrice=Double.parseDouble(B[3]);
            dessert[i].SetId(ID);
            dessert[i].SetName(Name);
            dessert[i].SetPrice(price);
            dessert[i].SetBuyPrice(BuyPrice);
            dessert[i].SetDessertQuantity(No);

        }
    }

    public String Search(int a) throws FileNotFoundException {
        for (int i=0;i< fm.getRowCount();i++){
            if(dessert[i].GetId()==a){
                searchid=i;
                break;
            }
        }
        return dessert[searchid].toString();
    }

    public void DeleteDessert(int a) throws IOException {
        Search(a);
        fm.delete_Rows(a);
        this.Array2Dessert();

    }

    public void UpdateDessert(int a , Dessert des) throws IOException {

        Search(a);
        fm.UpdateRow(searchid, des.toString());
        this.Array2Dessert();

    }

    public void IncreaseDessertQuantity(int a ,int b) throws IOException {

        Search(a);
        dessert[searchid].SetDessertQuantity(dessert[searchid].GetDessertQuantity()+b);
        this.Array2Dessert();

    }

    public void ReduceDessertQuantity(int a , int b) throws IOException {
        Search(a);
        dessert[searchid].SetDessertQuantity(dessert[searchid].GetDessertQuantity()-b);
        this.Array2Dessert();
    }

    public Dessert[] GetArray() throws FileNotFoundException {

        this.Array2Dessert();
        return this.dessert;
    }
    public int GetRowCount() throws FileNotFoundException {
        return this.fm.getRowCount();
    }








}
