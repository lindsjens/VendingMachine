package com.vendingmachine;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine {
    //formats price to 1.95 instead of 1.950001378- for log
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    //create Map that .txt file will add values to
    Map <String, Inventory> items = new TreeMap<String, Inventory>();
    //create balance and set it to 0
    private double balance = 0;

    //Scan the file (VendingMachine.txt) and make it display to the user
    public void updateInventoryList() {
        try (Scanner fileReader = new Scanner(new File("ExampleFiles/VendingMachine.txt"))) {
            //while a line exists, keep reading
            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();
                //go up to each | and put the values into a new array
                String[] lineToArray = line.split("\\|");
                //assign these variables to the different indexes in the array
                String location = lineToArray[0];
                String name = lineToArray[1];
                double price = Double.valueOf(lineToArray[2]);
                String inventoryType = lineToArray[3];
                if (inventoryType.equals("Chip")) {
                    Chips chips = new Chips(location, name, 5, price);
                    items.put(location, chips);
                }
                if (inventoryType.equals("Candy")) {
                    Candy candy = new Candy(location, name, 5, price);
                    items.put(location, candy);
                }
                if (inventoryType.equals("Drink")) {
                    Drink drink = new Drink(location, name, 5, price);
                    items.put(location, drink);
                }
                if (inventoryType.equals("Gum")) {
                    Gum gum = new Gum(location, name, 5, price);
                    items.put(location, gum);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }


    }

    public VendingMachine(){
        updateInventoryList();
    }


    //method for when the user wants to see the list of items available
    public String getDisplayForItemsString(){
        String display = "";
        for (Inventory item : items.values()){
            if (item.getAmount() <= 0) {
                display += item.getLocation() + " " + item.getName() + " " + item.getPrice() + " " + "Item SOLD OUT" + "\n" ;
            }
            else {
                display += item.getLocation() + " " + item.getName() + " " + item.getPrice() + " " + item.getAmount() + "\n" ; //add getPrice and add Location
            }
        }
        return display;
    }

    //user wants to add money, get balance
    public double getBalance(){
        return Double.parseDouble(decimalFormat.format(balance));
    }

    public double addMoney(double amountToAdd) throws FileNotFoundException {
        balance +=amountToAdd;
        //TODO: call writeToLogFile for the FEED MONEY scenario
        //this part is adding to the log
        String outputString = "FEED MONEY: $" + amountToAdd + " $" + getBalance();
        writeToLogFile(outputString);

        return balance;
    }

    //purchaseItem Method
    public String purchaseItem(String selectedLocation) throws FileNotFoundException {
        Inventory selectedItem = items.get(selectedLocation);
        //put if the item is sold out first, otherwise it was saying it was sold out but still vending
        if (selectedItem.getAmount() < 1){
            System.out.println("SOLD OUT");

        }
        //user has the money and the item is in stock, actual vending/purchase portion
        else if (balance >= selectedItem.getPrice()){
            balance = balance - selectedItem.getPrice();
            //how do we update subtracting the amount?
            selectedItem.purchaseOneItem();
            return (selectedItem.makeNoise()) + ("\nPlease enjoy your " + selectedItem.getName());

        } else if (balance <= 0){
            //user has a balance of 0, or tries to put in negative money
            return ("Come back with some money"); //return strings instead of printing out
        } else if (balance < selectedItem.getPrice()){
            // if item costs more than balance --> return error message
            return ("You need more money for that");
        }


        //purchase part of writing to log, keeping track of what was purchased
        String outputString = selectedItem.getName() + " " + selectedItem.getLocation() + " $"
                + selectedItem.getPrice() + " $" + getBalance();
        writeToLogFile(outputString);


        return selectedItem.getName();
    }


    public String returnChange() throws FileNotFoundException {

        //convert balance into pennies, divide by the change
        int balanceInPennies = (int)(balance*100);

        int quarter = 25;
        int dime = 10;
        int nickel = 5;

        double amountOfChangeBeingReturned = balance;
        //what the user will receive
        int returnedQuarters=0;
        int returnedDimes=0;
        int returnedNickels=0;

        if(balanceInPennies >0){
            //gives the user the number of quarters
            returnedQuarters = balanceInPennies / quarter;
            //checks for a remainder, which is the new balanceInPennies
            balanceInPennies = balanceInPennies % quarter;
        }

        if(balanceInPennies >0) {//if there was a remainder, do dimes
            returnedDimes = balanceInPennies / dime;
            balanceInPennies = balanceInPennies % dime;
        }

        if(balanceInPennies >0) {//if there was a remainder, do nickels
            returnedNickels = balanceInPennies / nickel;
            balanceInPennies = balanceInPennies % nickel;
        }

        balance = balanceInPennies;

        //TODO: call writeToLogFile for the GIVE CHANGE scenario\
        String outputString = "GIVE CHANGE: $" + decimalFormat.format(amountOfChangeBeingReturned) +" $" + getBalance();
        writeToLogFile(outputString);

        return "Your change is " + returnedQuarters + " quarters," + " " + returnedDimes + " dimes," + " " + returnedNickels + " nickels";

    }



    //method for writing to log
    public void writeToLogFile(String outputString) throws FileNotFoundException {

            File logFile = new File("ExampleFiles/Log.txt");
            FileOutputStream outputToLog = new FileOutputStream(logFile, true); //opening with append
            try (PrintWriter writeToLog = new PrintWriter(outputToLog)) {
                //formats date to one shown in original log
                //all three log calls start the same way, this is the setup (date, time)
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a ");
                String currentDate = simpleDateFormat.format(new Date());
                //calling on outputString in the methods above, adding their information to the date
                String output = currentDate + outputString +"\n";
                writeToLog.write(output);
            }
    }
}
