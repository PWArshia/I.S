package com.example.i_s.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TextFile_Manager {
    private String FileName;
    private String FilePath;
    private int CRow;
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





}
