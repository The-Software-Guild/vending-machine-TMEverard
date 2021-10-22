package Vending.UI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import Vending.DTO.Item;
import Vending.Service.Coins;

public class VendingView {
        private UserIO io;
        public VendingView(UserIO io) {
            this.io = io;
        }
        //Constructor
        public int enterOrExit() {
            io.print("Would you like to buy or exit?");
            io.print("1 - Enter money");
            io.print("2 - Exit");
            return io.readInt("Please select from the above choices either 1 or 2.", 1, 2);
        }

        public BigDecimal enterMoney(){
            io.print("Enter your money: ");
            String money = io.readString("Money here: ");
            return new BigDecimal(money);

        }
        public String pick(){
            io.print("Select your product");
            return io.readString("Please select from the above choice by name.");
        }

        public void selection(List<Item> itemList){
            io.print("The options are: ");
                int i = 0;
                 for (Item s : itemList) {
                     //Only selects it to be shown if the product is in stock
                     if(s.getNumberOf()>0){
                     i++;
                    io.print(i + " - Product= " + s.getName() + " Cost= " + s.getCost() + " Number left= " + s.getNumberOf());
                    }
                 }

        }

        public void displayExitBanner() {
            io.print("Good Bye, program is exiting");
        }

        public void displayUnknownCommandBanner() {
            io.print("Incorrect selection");
        }

        public void displayErrorMessage(String errorMsg) {
            io.print("=== ERROR ===");
            io.print(errorMsg);
        }

    public void giveCoins(ArrayList coinsGiven) {
            io.print("Here is your change: ");
            int j = 0;
            for(Coins coin : Coins.values()) {
                io.print(coin + " " + coinsGiven.get(j));
                j++;
            }
    }
}
