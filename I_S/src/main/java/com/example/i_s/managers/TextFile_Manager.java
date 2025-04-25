package com.example.i_s.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class TextFile_Manager {
    private String FileName;
    private String FilePath;
    private int CRow;
    private String Data[];

    public TextFile_Manager(String FileName) {
        this.FileName = FileName;
        CRow=0;
    }

    public void CreateTextFile() throws IOException {
        File file = new File(FileName);
        FilePath = file.getAbsolutePath();
        FileWriter fw = new FileWriter(file);
        fw.close();
    }

    public String getAddress(){
        return FilePath;
    }

    public void ClearTextFile() throws IOException {
        this.CreateTextFile();
    }

    public void AppendRow(String row) throws IOException {
        File file = new File(FileName);
        FileWriter fw = new FileWriter(file,true);
        fw.append(row+"\n");
        fw.close();
        CRow++;
    }

    private void File2Array() throws FileNotFoundException{

        File file= new File(FilePath);
        Scanner fw=new Scanner(file);
        Data=new String[1000];
        CRow=0;
    
        while (fw.hasNextLine()){
            Data[CRow]=fw.nextLine();
            CRow++;
        }
        fw.close();
    }

    private void Array2File() throws IOException{

        File file= new File(FilePath);
        FileWriter fw = new FileWriter(file,true);
        for (int i=0; i<CRow; i++){
            fw.append(Data[i]+"\n");
        }
        fw.close();
    }



    public void delete_Rows(int a) throws IOException{

        File file=new File(FilePath);
        FileWriter fw = new FileWriter(file,true);
        for (int i=0; i<CRow ; i++){
            if(i!=a){
                fw.append(Data[i]+"\n");
            }
        }
        fw.close();
    }


    private void Invert_Rows(int a, String s) throws FileNotFoundException{
        File2Array();






    }






}
