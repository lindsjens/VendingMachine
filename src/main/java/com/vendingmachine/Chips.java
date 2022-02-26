package com.vendingmachine;

public class Chips extends Inventory{


    @Override
    public String makeNoise() {
        return "Crunch Crunch, Yum!";
    }

    public Chips(String chipLocationName, String chipName, int chipAmount, double chipPrice){
        super(chipLocationName, chipName, chipAmount, chipPrice);
    }



}
