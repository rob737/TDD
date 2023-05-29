package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class DemoUtils {
    private String stringOne = "Robin";
    private String stringTwo = "Robin";
    private String[] strArray = {"Robin","Ruchika","Foster"};
    private List<String> listArray = List.of("Robin","Ruchika","Foster");

    public int add(int a, int b){
        return a+b;
    }

    public Object getReference(){
        return null;
    }

    public String getFirstReference(){
        return stringOne;
    }
    public String getSecondReference(){
        return stringTwo;
    }

    public String[] getFamilyMembers(){
        return strArray;
    }

    public List<String> getFamilyMembersList(){
        return listArray;
    }
}
