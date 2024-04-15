package com.example.projetmemory;

public class Donnees implements java.io.Serializable{

    private String name;
    public Donnees(String name){
        this.name = name;
    }
    // Getters
    public String getName(){return this.name;}


}