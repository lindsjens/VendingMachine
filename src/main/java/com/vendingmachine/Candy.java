package com.vendingmachine;

public class Candy extends Inventory{
    @Override
    public String makeNoise() {
        return "Munch munch, yum!";
    }

    public Candy(String candyLocationName, String candyName, int candyAmount, double candyPrice){
        super(candyLocationName, candyName, candyAmount, candyPrice);
    }


}
