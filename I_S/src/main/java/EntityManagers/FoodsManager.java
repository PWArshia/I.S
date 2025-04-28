package EntityManagers;

import Common.Commons;
import Common.Foods;
import TxtFileManager.TextFile_Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FoodsManager {
    private TextFile_Manager FoodFileManager;
    private Foods[] FoodsList=new Foods[1000];
    int cFoodsList=0;


    public FoodsManager(String fileName) throws IOException {
        FoodFileManager = new TextFile_Manager(fileName);
        FoodFileManager.CreateTextFile();
        TextFile2Array();
    }

    private void Array2TextFile() throws IOException {
        FoodFileManager.ClearTextFile();
        for (int i = 0; i < cFoodsList; i++) {
            FoodFileManager.AppendRow(FoodsList[i].toString());
        }
    }


    private void TextFile2Array() throws FileNotFoundException {
        String[] A = FoodFileManager.getArray();
        int cA=FoodFileManager.getRowCount();
        if(A[0]=="") {
            A[0] = null;
            cA--;
        }
        for (int i = 0; i < cA; i++) {
            String[] B = A[i].split(Commons.Commons);

            FoodsList[i]=new Foods(Integer.parseInt(B[0]),B[1],B[2],
                    Double.parseDouble(B[3]),Integer.parseInt(B[4]),Double.parseDouble(B[5]));

        }
        cFoodsList=cA;
    }


    public void AddFood(Foods F) throws IOException {
        FoodFileManager.AppendRow(F.toString());
        TextFile2Array();
    }


    public boolean DeleteFood(Foods F) throws IOException {
        int a=IndexSearchFood(F.GetID());
        if (a==-1)
            return false;
        FoodFileManager.delete_Rows(a);
        TextFile2Array();
        return true;
    }



    public void UpdateFood(Foods F,Foods F1) throws IOException {
        int a=IndexSearchFood(F.GetID());
        FoodFileManager.UpdateRow(a,F1.toString());
        TextFile2Array();
    }




    public String SearchFood(int ID) throws IOException {

        for (int i = 0; i < cFoodsList; i++) {
            if (this.FoodsList[i].GetID()==ID) {
                return FoodsList[i].toString();
            }
        }
        return null;

    }



    public int IndexSearchFood(int ID) throws IOException {
        for (int i = 0; i < cFoodsList; i++) {
            if (this.FoodsList[i].GetID()==ID) {
                return i;
            }
        }
        return -1;
    }


    public void SetArray(Foods[] F, int cF) throws IOException {
        FoodsList=F;
        cFoodsList=cF;
        Array2TextFile();
    }
    public Foods[] GetArray() throws FileNotFoundException {
        TextFile2Array();
        return FoodsList;
    }
    public int GetLengthArray() {
        for (int i = FoodsList.length-1; i >=0; i--) {
            if (FoodsList[i]!=null){
                cFoodsList=i+1;
                break;
            }
        }

        return cFoodsList;
    }




}
