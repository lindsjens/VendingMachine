package com.vendingmachine;

import java.math.BigDecimal;

public abstract class Inventory {
    //instance variables
    private String location;
    private String name;
    private int amount = 5;
    private double price;
    private String noise;

    //getters
    public String getLocation(){return this.location;}
    public String getName(){return this.name;}
    public double getPrice(){return this.price;}
    public int getAmount() {return this.amount;}

    public abstract String makeNoise();

    public int purchaseOneItem(){
        //return amount
        amount = amount -1;
        return amount;
    }

    //constructor
    public Inventory (String location, String name, int amount, double price){
        this.location = location;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }


}
