package com.vendingmachine;

public class Drink extends Inventory{

    @Override
    public String makeNoise() {
        return "Glug glug, Yum!";
    }

    public Drink(String drinkLocationName, String drinkName, int drinkAmount, double drinkPrice){
        super(drinkLocationName, drinkName, drinkAmount, drinkPrice);
    }

}
