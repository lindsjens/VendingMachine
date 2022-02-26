package com.vendingmachine;

public class Gum extends Inventory{

    @Override
    public String makeNoise() {
        return "Chew Chew, Yum!";
    }

    public Gum(String gumLocationName, String gumName, int gumAmount, double gumPrice){
        super(gumLocationName, gumName, gumAmount, gumPrice);
    }

}
