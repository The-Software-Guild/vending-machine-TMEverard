package Vending.controller;

import Vending.DTO.Item;
import Vending.Dao.OutOfStockException;
import Vending.Dao.VendingPersistenceException;
import Vending.Service.Change;
import Vending.Service.VendingDataValidationException;
import Vending.Service.VendingServiceLayer;
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
                        //try {
                            view.selection(service.getAllItems());
                            BigDecimal funds = view.enterMoney();
                            String pick = view.pick();
                            Item purchase = service.getItem(pick);
                            BigDecimal price = purchase.getCost();
                            BigDecimal moneyLeft = change.changeGiven(funds, price);
                            ArrayList coinsGiven = change.coinsGiven(moneyLeft);
                            view.giveCoins(coinsGiven);
                        //}catch(VendingDataValidationException e){
                            System.out.println("Incorrect entry");
                        //}
                        try{
                        updateStock(pick);
                        }catch(OutOfStockException e){
                            System.out.println("Out of stock");
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

    private void updateStock(String purchaseName) throws VendingPersistenceException, OutOfStockException {
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
