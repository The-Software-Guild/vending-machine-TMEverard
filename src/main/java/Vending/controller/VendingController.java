package Vending.controller;

import Vending.DTO.Item;
import Vending.Service.*;
import Vending.Dao.VendingPersistenceException;
import Vending.UI.UserEntryException;
import Vending.UI.VendingView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VendingController {
    private VendingView view;
    private VendingServiceLayer service;
    private Change change;
    public VendingController(VendingServiceLayer service, VendingView view, Change change) {
        this.service = service;
        this.view = view;
        this.change = change;
    }
    public void run() {
        try {
            view.selection(service.getAllItems());
        }catch(VendingPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        try {
                            try {
                                view.selection(service.getAllItems());
                                BigDecimal funds = view.enterMoney();
                                String pick = view.pick();
                                try {
                                    try {
                                        try{
                                        Item purchase = service.getItem(pick);
                                        BigDecimal price = purchase.getCost();
                                        BigDecimal moneyLeft = change.changeGiven(funds, price);
                                        ArrayList coinsGiven = change.coinsGiven(moneyLeft);
                                        view.giveCoins(coinsGiven);
                                    } catch (OutOfStockException e) {
                                        System.out.println("Out of stock");
                                    }
                                    try {
                                        updateStock(pick);
                                    } catch (OutOfStockException e) {
                                        System.out.println("Cannot update the stock");
                                    }
                                } catch (VendingDataValidationException e) {
                                    System.out.println("Incorrect item entered");
                                }
                            } catch (InsufficientFundsException e) {
                                System.out.println("Insufficient funds");
                            }
                        } catch (UserEntryException e) {
                            System.out.println("Not correct monetary entry");
                        }
                }catch(NumberFormatException e){
                    System.out.println("Not correct monetary entry!");
                }

                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (VendingPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    private int getMenuSelection() {
        return view.enterOrExit();
    }

    public void updateStock(String purchaseName) throws VendingPersistenceException, OutOfStockException, VendingDataValidationException {
        Item purchase = service.getItem(purchaseName);
        if (purchase.getNumberOf() != 0) {
            service.updateStock(purchaseName, purchase);
        } else {
            throw new OutOfStockException("That item is out of stock!");
        }
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
