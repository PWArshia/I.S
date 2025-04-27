package EntityManagers;

import Common.Commons;
import Common.Drinks;
import Common.Foods;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DrinksManager {
    private TextFile_Manager DrinkFile_Manager;
    private Drinks[] DrinksList=new Drinks[1000];
    int cDrinksList=0;


    public DrinksManager(String fileName) throws IOException {
        DrinkFile_Manager = new TextFile_Manager(fileName);
        DrinkFile_Manager.CreateTextFile();
        TextFile2Array();
    }

    private void Array2TextFile() throws IOException {
        DrinkFile_Manager.ClearTextFile();
        for (int i = 0; i < cDrinksList; i++) {
            DrinkFile_Manager.AppendRow(DrinksList[i].toString());
        }
    }


    private void TextFile2Array() throws FileNotFoundException {
        String[] A = DrinkFile_Manager.getArray();
        int cA=DrinkFile_Manager.getRowCount();
        if(A[0]=="") {
            A[0] = null;
            cA--;
        }
        for (int i = 0; i < cA; i++) {
            String[] B = A[i].split(Commons.Commons);

            DrinksList[i]=new Drinks(Integer.parseInt(B[0]),B[1],B[2],Double.parseDouble(B[3]),Integer.parseInt(B[4]));

        }
        cDrinksList=cA;
    }


    public void AddDrink(Drinks D) throws IOException {
        DrinkFile_Manager.AppendRow(D.toString());
        TextFile2Array();
    }


    public boolean DeleteDrink(Drinks D) throws IOException {
        int a=IndexSearchDrink(D.GetID());
        if (a==-1)
            return false;
        DrinkFile_Manager.delete_Rows(a);
        TextFile2Array();
        return true;
    }



    public void UpdateDrink(Drinks D,Drinks D1) throws IOException {
        int a=IndexSearchDrink(D.GetID());
        DrinkFile_Manager.UpdateRow(a,D1.toString());
        TextFile2Array();
    }




    public String SearchDrink(int ID) throws IOException {

        for (int i = 0; i < cDrinksList; i++) {
            if (this.DrinksList[i].GetID()==ID) {
                return DrinksList[i].toString();
            }
        }
        return null;

    }



    public int IndexSearchDrink(int ID) throws IOException {
        for (int i = 0; i < cDrinksList; i++) {
            if (this.DrinksList[i].GetID()==ID) {
                return i;
            }
        }
        return -1;
    }


    public void SetArray(Drinks[] D, int cD) throws IOException {
        DrinksList=D;
        cDrinksList=cD;
        Array2TextFile();
    }
    public Drinks[] GetArray() {
        return DrinksList;
    }
    public int GetLengthArray() {
        return cDrinksList;
    }




}
