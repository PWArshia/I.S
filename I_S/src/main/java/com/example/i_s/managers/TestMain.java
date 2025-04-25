package com.example.i_s.managers;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        TextFile_Manager A=new TextFile_Manager("Hello");
        A.CreateTextFile();
        System.out.println(A.getAddress());
        A.AppendRow("salam");
        A.AppendRow("bedrood");
    }
}
