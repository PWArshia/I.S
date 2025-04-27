package EntityManagers;

import Common.Commons;
import Common.Dessert;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DessertManager {

    private TextFile_Manager fm;
    private Dessert dessert[]=new Dessert[10000];
    private int searchid;


    public DessertManager(String filename) throws IOException {
        fm = new TextFile_Manager(filename);
        fm.CreateTextFile();
        dessert=new Dessert[fm.getRowCount()+100];
    }

    public void AddDessert(Dessert dessert1) throws IOException {
        fm.AppendRow(dessert1.toString());
    }

    public void Array2Dessert() throws FileNotFoundException {

        String A[]=fm.getArray();

        for (int i=0;i<fm.getRowCount();i++){

            String B[]=A[i].split(Commons.Commons);

            int ID=Integer.parseInt(B[0]);
            String Name=B[1];
            int price=Integer.parseInt(B[2]);
            int No=Integer.parseInt(B[3]);
            dessert[i].SetId(ID);
            dessert[i].SetName(Name);
            dessert[i].SetPrice(price);
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

    public void UpdateDessert(int a) throws IOException {
        String b=Search(a);
        fm.UpdateRow(searchid,b);
        this.Array2Dessert();

    }









}
